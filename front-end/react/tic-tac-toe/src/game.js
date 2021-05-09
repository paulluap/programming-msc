/**
 *  [0, 1, 2]
 *  [3, 4, 5]
 *  [6, 7, 8]
 *  
 *   6 = 2*3 + 0
 *   7 = 2*3 + 1
 */
export function calculatewinner(squares){
  //len must be a square of some number
  const total = squares.length;
  const len = Math.sqrt(total);
  const indexOf = (i,j)=>i*len + j;
  const moveAndExpect = (expected, [i, j], nextStep, acc=[]) => {
    if (i<len && j<len && i>=0 && j>=0){
      acc.push([i,j]);

      if (!expected){
          return false;
      }

      if (expected !== squares[indexOf(i, j)]) {
        return false;
      }
      //ok current pass

      //has more to check
      const [inext, jnext] = nextStep(i, j);
      if (inext < len && jnext < len && inext >= 0 && jnext >= 0){
        return /* true && */moveAndExpect(expected, [inext, jnext], nextStep, acc);
      }

      //nothing more to see, return true
      return true;
    }
    return false;
  }
  const runCheck = (i,j)=> {
    const current = squares[indexOf(i,j)];
    let result = false
    if (i===0 && j===0){
      result = result || moveAndExpect(current, [i,j], (i,j)=>[i+1, j+1]);
    }
    if (i===0) {
      result = result || moveAndExpect(current, [i,j], (i,j)=>[i+1, j]);
    }
    if (j===0){
      result = result || moveAndExpect(current, [i,j], (i,j)=>[i, j+1]);
    }
    if (i===0 && j===(len-1)) {
      result = result || moveAndExpect(current, [i,j], (i,j)=>[i+1, j-1]);
    }
    return result;

  };

  //8 possiblilities
  for(var i=0; i<len; i++){
    for(var j=0; j<len; j++){
      if (runCheck(i,j)){
        const current = squares[indexOf(i,j)];
        return current;
      }
    }
  }
  return null;
}