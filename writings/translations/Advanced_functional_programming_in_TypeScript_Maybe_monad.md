> 翻译自 [Advanced functional programming in TypeScript: Maybe monad](https://codewithstyle.info/advanced-functional-programming-in-typescript-maybe-monad/)

从这篇博客开始， 我想做一个关于monad的简短系列。如果你对Javascript函数式编程有所了解[比如不可变(immutability)和纯函数(pure function)]，这里就带你进一步深入理解这种很酷的范式(paradigm)。无论你是否听说过monad, 或者听说过但不知道monad是什么，这个系列将尽可能用简单实用的词汇来解释monad。

我已经在博客上写过这个话题([monads in C#](https://codewithstyle.info/understand-monads-linq/), [monads in Scala](https://codewithstyle.info/scalas-option-monad-versus-null-conditional-operator-in-c/)), 但这次我想看看monad在前端的应用。多说一句 -- 我用Typescript而不是Javascript, 因为用强类型的语言更容易说明monad。但你不需要精通Typescript就可以理解这篇文章。

这个博客系列所有的代码都放在[github](https://github.com/miloszpp/typescript-monads)上了。你可以查看和这个系列相关的提交历史。

让我们开始monod的旅程吧。

#### 背景

我们要开发一个简单的应用, 实现以下场景: 一个公司有一个员工的层级结构 (每个员工可以有一个上级)。用户输入员工ID(数字)就可以看到他们上级的名字。我们先从不用monad的一般方法来实现。这里是UI的HTML代码：
```html
<body>
    <h1>Find employee's supervisor</h1>
    <p>
        <label for="employeeId">Enter employee ID</label>
        <input type="text" name="employeeId" id="employeeIdInput" />
    </p>
    <p>
        <button type="button" id="findEmployeeButton">Find supervisor's name</button>
    </p>
    <p id="searchResults"></p>
</body>
```
这段HTML代码由员工 ID的输入框和搜索员工上级名字的按钮组成。组织界面行为的逻辑如下:

```javascript
import { EmployeeRepository } from "./employee.repository";

const employeeIdInputEl = document.getElementById("employeeIdInput") as HTMLInputElement;
const findEmployeeButtonEl = document.getElementById("findEmployeeButton");
const searchResultsEl = document.getElementById("searchResults");

const repository = new EmployeeRepository();

findEmployeeButtonEl.addEventListener("click", () => {
    const supervisorName = getSupervisorName(employeeIdInputEl.value);
    if (supervisorName) {
        searchResultsEl.innerText = `Supervisor name: ${supervisorName}`;
    } else {
        searchResultsEl.innerText = "Could not find supervisor for given id";
    }
});

function getSupervisorName(enteredId: string) {
    if (enteredId) {
        const employee = repository.findById(parseInt(enteredId));
        if (employee && employee.supervisorId) {
            const supervisor = repository.findById(employee.supervisorId);
            if (supervisor) {
                return supervisor.name;
            }
        }
    }
}
```
首先，我们取到某些HTML元素。接下来在button上加上点击处理函数。在点击处理函数里，我们调用`getSupervisorName` 函数。所有的逻辑都在`getSupervisorName`里面(具体逻辑稍后在看)。最后我们用查到的结果更新`p`这个标签。最后我们快速看一下class `EmployeeRepository`

```javascript
import { Employee } from "./employee.model";

export class EmployeeRepository {
    private employees: Employee[] = [
        { id: 1, name: "John" },
        { id: 2, name: "Jane", supervisorId: 1 },
        { id: 3, name: "Joe", supervisorId: 2 },
    ];

    findById(id: number) {
        const results = this.employees.filter(employee => employee.id === id);
        return results.length ? results[0] : null;
    }
}
```
这个类只是写死了一些内存中的员工层级结构。interface `Employee`长这样:
 ```javascript
export interface Employee {
    id: number;
    name: string;
    supervisorId?: number;
}
```

#### 嵌套，嵌套，嵌套

现在来关注`getSupervisorName`这个函数:

```javascript
function getSupervisorName(enteredId: string) {
    if (enteredId) {
        const employee = repository.findById(parseInt(enteredId));
        if (employee && employee.supervisorId) {
            const supervisor = repository.findById(employee.supervisorId);
            if (supervisor) {
                return supervisor.name;
            }
        }
    }
}
```
正如我们看到的一样，函数体有这几层嵌套。这是因为查找上级(supervisor)的过程中要处理一些异常情况。
   - 用户可能没有输入员工ID就点了按钮
   - 输入的ID可能没有对应员工
   - 查找的员工可能没有上级(比如公司CEO或独立的顾问)
   - 可能员工上级的ID没有对应到某个员工(数据有误)

换句话说，这里调用了很多方法(operation)而且每个方法都可能返回空值(空的用户输入，空的查找结果等等)。这个函数需要处理所有的异常case，因此需要嵌套的if语句。这样做有问题吗？我想是的：
   - 写这样的代码很容易漏掉某些情况的判断，编译器也检查不出来
   - 代码可读性差

我们来看一下如何能同时解决这两个问题。

#### 引入Maybe

简化代码的方式是识别出一个模式，并且通过抽象来影藏模式的实现细节。这里反复出现的模式就是`getSupervisorName`里面的嵌套if语句。

```javascript
if (result) {
  const nextResult = operation(result);
  if (nextResult) {
     // and so on...
  }
} // else stop
```

但是如何抽象这种模式呢？出现这个if语句的原因是result变量保存的值可能是空值。我们来构造一个包装类(wrapper)来持有这个值并且能够表达出这个值是否为空值(即`null`或`undefined`或空string)的情况。将这个包装类命名为`Maybe`:

```javascript
export class Maybe<T> {
    private constructor(private value: T | null) {}

    static some<T>(value: T) {
        if (!value) {
            throw Error("Provided value must not be empty");
        }
        return new Maybe(value);
    }

    static none<T>() {
        return new Maybe<T>(null);
    }

    static fromValue<T>(value: T) {
        return value ? Maybe.some(value) : Maybe.none<T>();
    }

    getOrElse(defaultValue: T) {
        return this.value === null ? defaultValue : this.value;
    }
}
```

`Maybe`的实例持有一个`value`, `value`可能是一个真实的值也可能是`null`。这里`null`是一个表达空值的内部表示。构造函数声明为private, 这样只能通过调用`Maybe`的静态方法`some`或`none`来构建`Maybe`实例。最后，`getOrElse`可以安全地提取`Maybe`持有的值。调用的时候必须传入缺省值，`Maybe`是空的时候将返回这个缺省值。到这里应该一切都OK。我们现在可以显示地表达一个方法返回一个可能为空的值。修改`EmployeeRepository`的`findById`方法:

```javascript
findById(id: number): Maybe<Employee> {
    const results = this.employees.filter(employee => employee.id === id);
    return results.length ? Maybe.some(results[0]) : Maybe.none();
}
```

注意`findById`的返回值现在更有意义了，更好的捕获了开发者的意图。`findById`的确可能返回一个空值，比如员工ID在repository里不存在。更进一步我们可以修改`Employee`接口，来表明`supervisorId`可能是一个为空的值。

```javascript
export interface Employee {
    id: number;
    name: string;
    supervisorId: Maybe<number>;
}
```
要让`Maybe`类变得有用，我们要添加一些方法。你已经知道数组上可以调用`map`方法，对吧？就是把一个方法应用到数组的每个元素上。如果我们把Maybe看做一个特殊的数组，即只有一个值或0个值的数组，那么我们也定义一个类似的`map`方法供我们使用。

```javascript
map<R>(f: (wrapped: T) => R): Maybe<R> {
    if (this.value === null) {
        return Maybe.none<R>();
    } else {
        return Maybe.fromValue(f(this.value));
    }
}
```

我们的`map`方法传入一个对持有的值进行某种变换`f`函数，并且把变换的结果包装成一个新的`Maybe`实例返回出去。如果`Maybe`是`none`，返回出去的也是空的`Maybe`（就像在空的数组上调用`map`方法会返回一个空的数组）。`R`是类型参数，表示`f`转换函数的返回类型。那如何使用`map`呢？原始版本的`getSupervisorName`方法包含下面`if`语句:
```
const supervisor = repository.findById(employee.supervisorId);
if (supervisor) {
    return supervisor.name;
}
```

但现在`findById`会返回一个`Maybe`! 碰巧我们定义了`map`方法，里面的逻辑和这里`if`的语义是一样的！因此，我们可以把上面的代码改成:

```javascript
const supervisor: Maybe<Employee> = repository.findById(employee.supervisorId);
return supervisor.map(s => s.name);
```

我们是不是把`if`语句通过抽象隐藏起来了？是的，我们做到了！然而目前我们还不能把整个方法改成这种风格。

#### 用`map`, 还是`flatMap` ?
上面的转换用`map`方法是可行的，但如果是这种情况:

```javascript
const employee = repository.findById(parseInt(enteredId));
if (employee && employee.supervisorId) {
    const supervisor = repository.findById(employee.supervisorId);
    // ...
}
```

我们可以尝试改成用`map`:
```javascript
const employee: Maybe<Employee> = repository.findById(parseInt(enteredId));
const supervisor: Maybe<Maybe<Employee>> = employee.map(e => repository.findById(e.supervisorId));
```
看到问题了吗？`supervisor`的类型是`Maybe<Maybe<Employee>>`。这是因为我们的变换函数现在把一个基本的值映射(map)到了一个`Maybe`类型的值（之前我们只是从一个基本值映射到另一个基本值）。是否有办法把`<Maybe<Maybe<Employee>>`变换成一个简单的`<Mapbe<Employee>>`? 或者说，我们想把我们的`Maybe`扁平化(flatten)。再次借用数组的类比。你可以扁平化(`[...].flatten()`)嵌套数组`[[1,2,3], [4,5,6]]` => `[1,2,3,4,5,6]`。我们在`Maybe`里面添加一个新的方法`flatMap`。作用和`map`很像，但是这个方法会把结果扁平化，消除嵌套的`Maybe`
```
flatMap<R>(f: (wrapped: T) => Maybe<R>) : Maybe<R> {
    if (this.value === null) {
        return Maybe.none<R>();
    } else {
        return f(this.value);
    }
}
```
实现很简单。如果`Maybe`实例持有的不是空值我们就提取这个值，传给`f`，然后把`f`的返回值返回出去（返回一个空或非空的`Maybe`）。如果`Maybe`实例是空的，我们就返回一个空的`Maybe`。注意`f`的函数签名，之前是`T=>R`，现在是`T=>Maybe<R>`。有了`flatMap`之后，我们就能够把上面代码重写成:
```
const employee: Maybe<Employee> = repository.findById(parseInt(enteredId));
const supervisor: Maybe<Employee> = employee.flatMap(e => repository.findById(e.supervisorId));
```

#### Maybe Monad 实战

现在，我们所有准备工作做好了，可以重写`getSupervisorName`
```javascript
function getSupervisorName(maybeEnteredId: Maybe<string>): Maybe<string> {
    return maybeEnteredId
        .flatMap(employeeIdString => Maybe.fromValue(parseInt(employeeIdString))) // parseInt can fail
        .flatMap(employeeId => repository.findById(employeeId))
        .flatMap(employee => employee.supervisorId)
        .flatMap(supervisorId => repository.findById(supervisorId))
        .map(supervisor => supervisor.name);
}
```

我们消除了所有的嵌套`if`语句！`getSupervisorName`的实现变成了对输入值的一个优雅的转换管道。处理空值的细节代码都是一些模板代码而且会使代码的真正意图不明显，因此需要把这些细节隐藏起来。现在使用`Maybe`做到了。注意如果传给`flatMap`的任何方法返回`none`, 整个`flatMap`连就会立即返回`none`。这和之前的`if`语句是一致的逻辑。下面是在点击监听函数中的使用:
```
findEmployeeButtonEl.addEventListener("click", () => {
    const supervisorName = getSupervisorName(Maybe.fromValue(employeeIdInputEl.value));
    searchResultsEl.innerText = `Supervisor name: ${supervisorName.getOrElse("could not find")}`;
});
```

你应该猜到了，`Maybe`就是一个 **monad**！ monad的正式定义是支持下面两种操作的容器类型:
   - `return` - 从一个普通的值构造一个容器类型的值(这里的`none`和`some`)
   - `bind` - 可以组合monad值 (`flatMap`)

monad也必须尊崇一些monad法则，我们暂时先不看。目前，你可以相信我我们的`Maybe`实现已经准守了monad法则！

#### 总结

这篇是这个系列的第一篇，我们构造了第一个monad。`Maybe`monad的目的是抽象出处理空值的逻辑。多亏了这个引入这个类型，我们现在写代码就不用担心如何处理空值的情况了。在下一篇，我们会看一下使用Typescript把使用monad的代码变得更加可读。你有没有发现monad很有趣?