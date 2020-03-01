
export class Observable<T> { 
    private _subscribe: any
    constructor(subscribe?:any){
        if (subscribe) {
            this._subscribe = subscribe;
        }
    }

    subscribe(onNext: any, onError?: any, onCompleted?:any){
        if (typeof onNext === 'function') {
            this._subscribe({
                onNext: onNext,
                onError: onError || (()=>{}),
                onCompleted: onCompleted || (()=>{})
            })
        }else{
            this._subscribe(onNext)
        }
    } 

    map <O> (projectionFn: (i: T)=>O) : Observable<O>{
        //pass observer at this stage to the previous observable
        return new Observable(observer=>{
            //this : the original observable before map is applied
            //whenever we call the new observer, we first call this (previous in relation to new)
            //, with a decorated observer passed to this
            //and if this is again a mapped observable, then it calls previous observable first 
            //so we will see [map] emit happens after previous observable, 
            //this order applies recusively
            console.log('[map] subscribe previous ')
            //this see order is the reverse of the execution order
            /**
             * subscribe order
             * [map] subscribe previous
             * [map] subscribe previous
             * [of] see original array
             * 
             * execution order:
             * ->[of] emit 2
             * ->[map] emit 2
             * ->[map] emit 5
             * 
             */
            return this.subscribe(
                //this part is onNext same as if we passed in {onNext: ...}
                val => {
                    console.log(`->[map] emit ${val}`)
                    observer.onNext(projectionFn(val))
                },
                e=>observer.onError(e),
                ()=>observer.onCompleted()
            )
        })
    }

    filter (predicateFn : (t: T)=>boolean) : Observable<T> {
        return new Observable(observer=>{
            //subscribe to previous
            console.log(`[filter] subscribe previous`)
            this.subscribe(
                val => {
                    if (predicateFn(val)){
                        console.log(`->[filter] emit ${val}`)
                        //forward to next observer
                        observer.onNext(val)
                    }
                },
                e => observer.onError(e),
                () => observer.onCompleted()
            )
        })
    }

    take(n: number) : Observable<T>{
        return new Observable(observer=>{
            let currentCount = 0;
            this.subscribe(
                val=>{
                    if (currentCount == n){
                        //TODO: unscribe and cleanup
                        observer.onCompleted()
                        return;
                    }
                    observer.onNext(val)
                    currentCount ++;
                },
                e=>observer.onError(e),
                ()=>observer.onCompleted()
            )
        })
    }

    static of <T> (...args: T[]) : Observable<T>{
        return new Observable((observer)=>{
            console.log('[of] see orignal array')
            args.forEach(v=>{
                console.log(`->[of] emit ${v}`)
                observer.onNext(v)
            })
            observer.onCompleted()
        });
    }

    static from <T> (iterable: T[]): Observable<T>{
        return new Observable(observer=>{
            iterable.forEach(v=>observer.onNext(v))
            observer.onCompleted()
        })
    }

    static fromEvent(source, event): Observable<any>{
        return new Observable(observer=>{
            const callbackFn = e=>observer.onNext(e)
            source.addEventListener(event, callbackFn)
            return {
                unSubscribe : ()=>{
                    source.removeEventListener(event, callbackFn)
                }
            }
        })
    }
}


Observable.of(1,2,3,4,5,6,7,8,9,10)
.map(i=>i+1)
.filter(i=>i%2==0)
.take(3)
.subscribe(e=>console.log(e));
// Observable.from([1,2,3]).subscribe(e=>console.log(e));
