const gameData = [
    {
      title: 'Mega Man 2',
      bosses: [
        { name: 'Bubble Man', weapon: 'Bubble Beam' },
        { name: 'Metal Man', weapon: 'Metal Blade' } 
       ]
    },
    {
      title: 'Mega Man 3',
      bosses: [
        { name: 'Gemini Man', weapon: 'Gemini Laser' },
        { name: 'Top Man', weapon: 'Top Spin' }
        ]
    }
  ];

Array.prototype.flatMap = function(mapFn /*(e)=>[] */){
    let results = []
    for(let i=0; i<this.length; i++){
        results = results.concat(mapFn(this[i]))
    }
    return results
}

console.log(gameData.flatMap(g=>g.bosses))