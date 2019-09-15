### Advanced functional programming in TypeScript: monads and generators

> 翻译自 [Advanced functional programming in TypeScript: monads and generators](https://codewithstyle.info/advanced-functional-programming-typescript-monads-generators/)

欢迎来到这个系列的第二篇。在第一篇里，你已经用Typescript创建了第一个monad。在这篇，你会看到如何使用generator使得monad代码更有可读性。
你可已在这个[库](https://github.com/miloszpp/typescript-monads)里找到所有代码。请查阅和这部分有关的代码分支。

#### 生成器函数
[ES6标准](http://es6-features.org/#GeneratorControlFlow)的一部分引入了生成器函数。生成器函数是一种特殊的函数，可以在执行中暂停。这听起来有些反直觉，如果你觉得javascript是单线程执行的，并遵守运行到结束方式(run-to-completion)。然而，有了生成器，代码依然是同步执行的。暂停执行以为这把控制返还给调用函数。调用函数然后在任一一点恢复执行。我们看一个简单例子: 

```javascript
function *numbers(): IterableIterator<number> { 
    console.log('Inside numbers; start');
    yield 1;
    console.log('Inside numbers; after the first yield');
    yield 2;
    console.log('Inside numbers; end');
}
```

这里有两个新的语法。一个是`function`关键字后面的`*`。这个说明`numbers`不是一般的函数，而是一个生成器函数。另一个新的关键字是`yield`(产生)。这个和`return`有点像，但可以在方法体中多次执行。产生(yield)一个值，生成器方法就会给调用者返回这个值。然而，不像`return`一样，调用者决定是否恢复执行并把控制交还给这个生成器函数。恢复后，执行将从上一个`yield`语句开始。我们先调用`numbers`来取到一个生成器实例: 
```javascript
const numbersGenerator = numbers();
```

在这一点上，`numbers`函数没有一行是执行的。要执行, 我们需要调用`next`。生成器函数将会执行到它遇到第一个`yield`之前，然后控制会交还给调用者。

```javascript
console.log('Outside of numbers'); 
console.log(numbersGenerator.next());
console.log('Outside of numbers; after the first next');
console.log(numbersGenerator.next());
console.log('Outside of numbers; after the second next');
```

运行这段代码，会得到下面输出: 
```
Outside of numbers
Inside numbers; start
{value: 1, done: false}
Outside of numbers; after first next
Inside numbers; after the first yield
{value: 2, done: false}
Outside of numbers; after the second next
```

你可以看到，执行在`numbers`和调用函数之间来来回回。每次调用`next`会返回一个包含产生的值和是否结束的标志位`done`的`IteratorResult`。标志位`done`表示在`numbers`里面是否还有代码执行。

!(https://codewithstyle.info/images/2018/02/Generators.png)

生成器是一个强大的机制。一个用到的地方是构架无限懒加载序列(lazy, infinite sequences)。另一个是协程(co-routes) - 一种不同两部分代码可以通信的并发模型。

#### 使用生成器实现的Maybe
回忆一下，我们之前用这种方式实现了`getSupervisorName`: 
```javascript
function getSupervisorName(maybeEnteredId: Maybe<string>): Maybe<string> {
    return maybeEnteredId
        .flatMap(employeeIdString => Maybe.fromValue(parseInt(employeeIdString)))
        .flatMap(employeeId => repository.findById(employeeId))
        .flatMap(employee => employee.supervisorId)
        .flatMap(supervisorId => repository.findById(supervisorId))
        .map(supervisor => supervisor.name);
}
```
显然，这个代码比一开始深层嵌套的代码可读性更好了。但是，这和通常的过程式代码很不一样。我们是否可以改成像过程是一样的代码呢？我们知道，生成器可以暂停一个函数的执行，然后再回复。这意味着我们可以在`yield`语句之间插入一些需要执行的代码。我们可以写一个方法，接受一个生成器方法，然后插入一些处理空值的代码。我们来实现一个用`yield`语句来处理空值的`getSupervisorName`函数:
```javascript
function* () { 
    const enteredIdStr = yield maybeEnteredId
    const enteredId = parseInt(enteredIdStr)
    const employee = yield repository.findById(enteredId)
    const supervisorId = yield employee.supervisorId
    const supervisor = yield repository.findById(supervisorId)
    return Maybe.some(supervisor.name)
}
```
我们假定maybeEnteredId在函数的比包里定义了，类型是`Maybe<string>。现在, `const enteredIdStr = yield maybeEnteredId`的语义是:
   - 如果`maybeEnteredId`有值得花，那么把这个值赋给`enteredIdStr`
   - 否则，整个方法返回`None`

换句话说，`yield`和`flatMap`起了一样的作用，但是语法不一样了。这对一个过程式编程的人来说更熟悉。


#### 实现Run

这还没有完成。我们还需要一个函数来消费这个生成器函数。换句话说，我们需要赋予这些`yield`语句以意义。我们定义一个`run`的函数。这个函数接受一个生成器方法并返回一个包含计算结果的`Maybe`实例。我们先实现一下`run`:

```javascript
static run<R>(gen: IterableIterator<Maybe<R>>): Maybe<R> { 
    let lastValue; 
    while(true) { 
        const result : IterableIterator<Maybe<R>> = gen.next(lastValue)
        if (result.done || result.value.value === null) { 
            return result.value
        }
        lastValue = result.value.value
    }
}
```
   1. `run`方法接受一个`gen`生成器函数，并描述了我们的计算。
   1. 我们进到一个无限循环中并调用`gen.next(lastValue)`。 这个调用会把控制流转到`gen`里面并执行到第一个`yield`（先忽略`lastValue`）
   1. 一旦上一步结束, 控制流会返回`run`。传给`yield`的值被包装在`IteratorResult`并作为`gen.next`的返回值。
   1. `result`有个标志位`done`。意味着在`gen`里面的代码有没执行完
   1. `result.value`持有`yield`的返回值。 是一个`Maybe`实例。英雌我们要查看是否是空值。如果是的话，我们就在整个计算中返回`None`。
   1. 否则，我们解开`Maybe`持有的值并赋给`lastValue`
   1. 以上循环不断重复。`lastValue`有值的时候，会传给`gen.next`作为调用`yield`的返回。


这里发生了很多。一种好的理解方式是把这两部分代码想象成互相通信。
   1. 调用`yield`, `gen`给`run`发送了一个`Maybe<T>`实例。
   1. `run`通过调用`gen.next(lastValue)` 回复了一个`T`实例

这里主要的意义是我们影藏了处理空值的逻辑，在调用者的角度，代码是这样的:
```javascript
function getSupervisorName(maybeEnteredId: Maybe<string>: Maybe<string> { 
    return Maybe.run(function* ()  {
        const enteredIdStr = yield maybeEnteredId;
        const enteredId = parseInt(enteredIdStr);
        const employee = yield repository.findById(enteredId);
        const supervisorId = yield employee.supervisorId;
        const supervisor = yield repository.findById(supervisorId);
        return Maybe.some(supervisor.name);
    }());
}
```

我们现在获得了一个干净优雅的`getSupervisorName`实现，所有的重复代码都在`run`里面。然而和`flatMap`方式不一样，这对一个过程是编程人员是更自然的。

#### 回到monad

你也许会说这很漂亮。但是和monad有什么关系呢？我们没有利用到`Maybe`monad的好处。让我们修复这个问题。你也许注意到了`run`和`flatMap`之间的相似性。两者都是处理空值并使用类似的逻辑: 如果一个`Maybe`实例是空的，那么返回None。否则，用解出来的持有的值继续计算。所以我们可以用`flatMap`实现`run` !

```javascript
static run<R>(gen: IterableIterator<Maybe<R>>): Maybe<R> { 
    function step(value?){
        const result = gen.next(value)
        if (result.done) { 
            return result.value
        }
        return result.value.flatMap(step);
    }
    return step()
}
```

这个递归的实现更加有函数式变成的思想:
   1. 我们定义了一个`step`函数，其接受一个可选的`value`参数，并传给`gen.next`。我们知道，这将会使得在`gen`里面继续执行，直到最近一个`yield`。
   1. 检查`result.done`, 如果是`false`(还有代码待执行), 我们只要在`result.value`上调用`flatMap`并递归地把`step`作为continuation函数传给`flatMap`。`flatMap`已经处理了空值的情况。
   1. 只要我们没碰到`None`, `step`递归调用会继续执行`gen`, 直到下一个`yield`。这个过程不断重复。

Client代码还是一样的。

#### 总结
在这片文章中，我们看到了如何通过生成器的使用提高了monad的使用体验。monad代码看上去更干净了，此外，团队合作中对习惯过程式变成的程序员也更容易了。这种使用生成器的方式也是`async/await`机制的基础。在后面的文章中，你会学到`Promise`也是monad并且`async/await`只不过是 `function*/yield` 的一种特殊变体。但这里先不去提前讨论:)

