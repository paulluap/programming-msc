class Subject {
    constructor(){
        this.callbacks = []
    }

    subscribe(fn){
        this.callbacks.push(fn)
    }

    publish(data){
        this.callbacks.forEach(element => {
            element(data)
        });
    }
}

const subject = new Subject();
const observer1 = data => console.log(`Observer1 received data ${data}`)
subject.subscribe(observer1)
setTimeout(() => {
    subject.publish('test data ')
}, 1000);