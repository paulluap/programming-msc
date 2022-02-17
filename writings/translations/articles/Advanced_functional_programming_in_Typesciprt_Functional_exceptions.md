### Advanced functional programming in Typescript: functional exceptions
> 翻译自[Advanced functional programming in TypeScript: functional exceptions](https://codewithstyle.info/advanced-functional-programming-typescript-functional-exceptions/)

在看完第一部分和第二部分的Maybe实现后，我们再看一个有用的Monad例子。在这片文章中，我们介绍一个函数式编程中如何解决异常问题。你可以在[这里](https://github.com/miloszpp/typescript-monads/tree/part-3-exceptions-and-result/src)找到源代码。

##### 异常(Exceptions)
异常是代码中处理错误和意料之外情况的常用方式。在Java, C#，当然还有JavasScript等主流语言里都有异常。有趣的是有些语言(比如Rust)特意不引入异常。异常和函数式编程兼容吗？很遗憾，不是很兼容。举个例子，纯函数不应该有副作用。抛出异常其实就是一种副作用 - 导致你程序终止。更糟糕的是异常给代码带来了不可预测性。

```javascript
function divide(x: number, y: number): number { 
    if (y === 0){
        throw new Error('Cannot divide by zero');
    return x / y;
}
```

虽然函数签名告诉我们`divide`返回`number`, 单并不是说总是会返回`number`。我们必须非常小心，并记得有一个异常错误要处理。然而，类型系统检查并不能确保我们一定要处理这个异常。

#### 更好的异常
如何显示地说明`divide`可能出现异常情况？我们来创建一个类 `Result<TSuccess, TFailure>`。还记得`Maybe<T>`是不是可以表示`Some`(某个值)或者`None`(空值)? 同样，`Result<TSuccess, TFailure>`表示要么是`Success`: 正常的执行的流程，要么是`Failure`: 错误情况(错误用类型`TFailure`表示)。也就是说，这个新类型的实例要么持有一个有效的结果，要么包含错误结果的相关信息。和异常不一样，现在显示的表达了一个错误可能发生。而且我们也知道了错误的类型(`TFailure`就是错误类型)。

#### 实现Result

我们从下面的`Result`定义开始: 
```javascript
export class Result<TSuccess, TFailure> { 
    private constructor(
        private value: TSuccess,
        private errorValue: TFailure
    ) { }

    static success<TSuccess, TFailure>(value: TSuccess) {
        return new Result<TSuccess, TFailure>(value, null);
    }

    static failure(TSuccess, TFailure>(errorValue: TFailure) {
        return new Result<TSuccess, TFailure>(null, errorValue);
    }
}
```

我们创建了一个类，只有两种方式被实例化 -- 通过`success`或`failure`静态方法。
类的内部持有一个`value`表示成功的结果，或者持有一个`errorValue`表示错误相关的信息。我们下面写一个简单的方法来获取`Result`持有的值。记住`Result`的实例要么是一个`Success`要么是一个`Failure`。因此，当获取值得时候我们一定要假设可能是错误的信息。我们需要提供`handleError`函数来处理错误情况:
```javascript
get (handleError: (errorValue: TFailure) => TSuccess): TSuccess {
    if (this.value === null){
        return handleError(this.errorValue);
    } else { 
        reutrn this.value;
    }
}
```
和`Maybe`类似，我们也需要一些让我们方便使用`Result`类型的操作。我们先定义`map`。在正常的情况下，`map`接受一个函数，并传给这个函数`Result`持有的值。然而，如果`Result`包含了一个错误，那么`map`就忽略传入的函数，直接返回这个错误。

```javascript
map<R>(f: (wrapped: TSuccess) => R) : Result<R, TFailure> {
    if (this.value === null){
        return Result.failure<R, TFailure>(this.errorValue)
    } else { 
        reutrn Result.success<R, TFailure>(f(this.value);
    }
}
```
如果我们传入的函数接受`Result`持有的值单也返回了一个`Result`! 我们就需要`flatMap`
```javascript
flatMap<R>(f: (wrapped: TSuccess)=>Result<R, TFailure>): Result<R, TFailure> {
    if (this.value === null) {
        return Result.failure<R, TFailure>(this.errorValue);
    } else {
        return f(this.value);
    }
}
```

#### `Result`实战
棒极了, 我们现在准备好了使用`Result`这个新的类了。我们改一下之前的代码，现在不用`Maybe`表示可能为空的值，而是用`Result`表示可能失败的值。
```javascript
findById(id: number): Result<Employee, string>{
    const results = this.employees.filter(employee => employee.id ==== id);
    return results.length 
        ? Result.success(results[0])
        : Result.failure("Employee does not exist");
}
```
我们更新了`findById`方法。现在如果查找的employee存在, 返回的结果包在了`Result.success`里面。否则，就返回包含错误相关信息的`Result.failure`。因此, `TFailure`在这里是一个`string`类型。接下来，我们更新一下模型。现在`Employee.supervisorId`也是一个`Result`类型。我们没有上级的employee也处理成一种异常情况。

```javascript
export interface Employee {
    id: number;
    name: string;
    supervisorId: Result<number, string>;
}

private employees: Employee[] = [
    { id: 1, name: "John", supervisorId: Result.failure("No supervisor") },
    { id: 2, name: "Jane", supervisorId: Result.success(1) },
    { id: 3, name: "Joe", supervisorId: Result.success(2) },
];
```

要使用上面的代码我们要调整一下`main.ts`文件。首先, 我们修改一下点击事件监听代码，让其根据HTML输入返回一个`Result`实例。接下来，`Result`传给`getSupervisorName`, 这个函数也会返回一个`Result`结果(我们一会就会看到)。最后，在获取`Result`里面的值时，我们提供一个回调函数来处理潜在的错误情况。

```javascript
findEmployeeButtonEl.addEventListener("click", () => {
    const inputResult: Result<string, string> = employeeIdInputEl.value
        ? Result.success(employeeIdInputEl.value)
        : Result.failure("No employee id provided");
    const supervisorNameOrError = getSupervisorName(inputResult)
        .get(error => `Error occured: ${error}`);
    searchResultsEl.innerText = `Supervisor name: ${supervisorNameOrError}`;
});
```
最后是`getSupervisorName`函数。这才是最有趣的地方，因为这个函数和之前用`Maybe`的时候看上去几乎一模一样!

```javascript
function getSupervisorName(enteredIdResult: Result<string,string>): Result<string,string> {
    return enteredIdResult
        .flatMap(safeParseInt)
        .flatMap(employeeId => repository.findById(employeeId))
        .flatMap(employee => employee.supervisorId)
        .flatMap(supervisorId => repository.findById(supervisorId))
        .map(supervisor => supervisor.name)
}


function safeParseInt(numberString: string): Result<number, string> {
    const result = parseInt(numberString);
    return isNaN(result)
        ? Result.failure("Invalid number format") : Result.success(result);
}

```

唯一调整的地方是类型签名和`safeParseInt`函数。事实证明，`map`和`flatMap`操作是如此通用，以至于它们可以用相同的代码处理两个不同的场景。希望您现在能看到Monad的力量！现在，您可以运行该程序并享受不错的错误消息。尝试不同的方案，例如提供不存在的ID，没有主管的员工ID，非数字ID等。

#### 总结 
在本文中，我们看到了如何使用Monad来用更函数式的方法替换异常。多亏了`Result`类型，我们可以显示地指出函数可能出现失败情况。更重要的是，我们迫使调用者始终假设某些地方可能出了问题并提供错误处理程序。

此示例中的错误处理已相当简化。我们使用字符串来传达错误消息。但是，没有什么可以阻止你使用更高级的类型来传递更多有意义的信息。例如，您可以使用区分的并集(discriminated unions)来表示不同类型的错误。你有没有觉得比起传统异常处理可读性更强了？


