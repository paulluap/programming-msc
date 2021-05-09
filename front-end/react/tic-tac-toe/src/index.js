import React from 'react';
import ReactDOM from 'react-dom';
import './index.css';
import { calculatewinner } from './game'

function Square(props) {
  return (
    <button 
      className="square" 
      onClick={props.onClick}>
      {props.value} 
    </button>
  );
}

class Board extends React.Component {


  renderSquare(i) {
    return (
      <Square 
        value={this.props.squares[i]}
        onClick={()=>this.props.onClick(i)}
      />
    );
  }

  render() {
    return (
      <div>
        <div className="board-row">
          {this.renderSquare(0)}
          {this.renderSquare(1)}
          {this.renderSquare(2)}
        </div>
        <div className="board-row">
          {this.renderSquare(3)}
          {this.renderSquare(4)}
          {this.renderSquare(5)}
        </div>
        <div className="board-row">
          {this.renderSquare(6)}
          {this.renderSquare(7)}
          {this.renderSquare(8)}
        </div>
      </div>
    );
  }

}

class Game extends React.Component { 

  constructor(props){
    super(props);
    this.state = { 
      history: [{
        squares: Array(9).fill(null),
      }],
      stepNumber: 0,
      xIsNext: true,
    }
  }

  /* player makes a move */
  handleClick(i) {

    //only shakeoff useless history if a player makes a move back in history
    const history = this.state.history.slice(0, this.state.stepNumber+1);
    const current = history[history.length-1];

    const squares = current.squares.slice();//copy
    
    //if square already filled, or game ends
    if (squares[i] || calculatewinner(squares)){
      return;
    }

    squares[i] = this.state.xIsNext ? 'X' : 'O';
    this.setState({
      history: history.concat([{
        squares
      }]),
      xIsNext: !this.state.xIsNext,
      stepNumber: history.length /* this.state.stepNumber + 1 */,
    })
  }

  jumpTo(i){
    this.setState({
      stepNumber: i,
      xIsNext: (i % 2) === 0
    })

  }

  render() { 
    const history = this.state.history;
    //render a point in history
    const current = history[this.state.stepNumber];
    const winner = calculatewinner(current.squares);
    let status = 'Next player: ' + (this.state.xIsNext ? 'X' : 'O');
    if (winner){
      status = 'winner: ' + winner;
    }

    const moves = history.map((_, move/*arr index*/)=>{
      const desc = move ? 
          'Go to move #' + move :
          'Go to game start ';
      return (
        <li key={move}>
          <button onClick={()=>this.jumpTo(move)}>{desc}</button>
        </li>
      );
    })

    return (
      <div className="game">
        <div className="game-board">
          <Board 
            squares={current.squares}
            onClick={(i)=>this.handleClick(i)} //must use => to reference the corrent this
          />
        </div>
        <div className="game-info">
          <div>{status}</div>
          <ol>{moves}</ol>
        </div>
      </div>
    )
  }

}


// ==================================================================

ReactDOM.render(
  <Game />, 
  document.getElementById('root')
)

