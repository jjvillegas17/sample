import java.io.*;
import java.util.*;
import java.awt.event.*;
import java.awt.*;
import javax.swing.*;

public class Game extends JPanel{
	final String PRINTX = "X";    // player
	final String PRINTY = "O";    // ai
	boolean hasWinner = false;
	String winner = null;

	String[][] board = new String[3][3];
	
	String turn = "X";
	int noOfTurn = 0;

	JButton b1=new JButton("");  
    JButton b2=new JButton("");  
    JButton b3=new JButton("");  
    JButton b4=new JButton("");  
    JButton b5=new JButton("");  
    JButton b6=new JButton("");
    JButton b7=new JButton("");
    JButton b8=new JButton("");
    JButton b9=new JButton("");

	public Game(){
		
		// this.board[0][0] = "O";   //tracing
		// this.board[0][1] = "X";
		// //this.board[0][2] = "X";
		// this.board[1][0] = "X";
		// //this.board[1][1] = "X";
		// //this.board[1][2] = "X";
		// this.board[2][0] = "X";
		// this.board[2][1] = "O";
		// this.board[2][2] = "O";


		this.setLayout(new GridLayout(3,3)); 

		this.add(b1);
		this.add(b2);
		this.add(b3);
		this.add(b4);
		this.add(b5);
		this.add(b6);
		this.add(b7);
		this.add(b8);
		this.add(b9);

		// b1.setFont(new Font("Arial", Font.PLAIN, 90));    //tracing
		// b1.setText(board[0][0]);
		// b1.setEnabled(false);

		// b2.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// b2.setText(board[0][1]);
		// b2.setEnabled(false);

		// // b3.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// // b3.setText(board[0][2]);
		// // b3.setEnabled(false);

		// b4.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// b4.setText(board[1][0]);
		// b4.setEnabled(false);

		// // b5.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// // b5.setText(board[1][0]);
		// // b5.setEnabled(false);

		// // b6.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// // b6.setText(board[1][2]);
		// // b6.setEnabled(false);


		// b7.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// b7.setText(board[2][0]);
		// b7.setEnabled(false);

		// b8.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// b8.setText(board[2][1]);
		// b8.setEnabled(false);

		// b9.setFont(new Font("Arial", Font.PLAIN, 90)); 
		// b9.setText(board[2][2]);
		// b9.setEnabled(false);


		ActionListener actionListener = new ActionListener() {
		  public void actionPerformed(ActionEvent e) {

		  	JButton clickedButton = (JButton) e.getSource();
		  	clickedButton.setFont(new Font("Arial", Font.PLAIN, 90)); 
			clickedButton.setText(turn);
			clickedButton.setEnabled(false);

		  	if(clickedButton == b1){
		  		board[0][0] = turn;
		  	}
		  	else if(clickedButton == b2){
		  		board[0][1] = turn;
		  	}
		  	else if(clickedButton == b3){
		  		board[0][2] = turn;
		  	}
		  	else if(clickedButton == b4){
		  		board[1][0] = turn;
		  	}
		  	else if(clickedButton == b5){
		  		board[1][1] = turn;
		  	}
		  	else if(clickedButton == b6){
		  		board[1][2] = turn;
		  	}
		  	else if(clickedButton == b7){
		  		board[2][0] = turn;
		  	}
		  	else if(clickedButton == b8){
		  		board[2][1] = turn;
		  	}
		  	else if(clickedButton == b9){
		  		board[2][2] = turn;
		  	}

		 //  	if(turn == PRINTY)
			// 	turn = PRINTX;
			// else
			// 	turn = PRINTY;

		  	String winner = checkBoardWinner();
		  	System.out.println("checking if has winner actionListener(hasWinner: " + hasWinner + ")");
		    if(winner == "X" || winner == "O"){
		    	b1.setEnabled(false);
		    	b2.setEnabled(false);
		    	b3.setEnabled(false);
		    	b4.setEnabled(false);
		    	b5.setEnabled(false);
		    	b6.setEnabled(false);
		    	b7.setEnabled(false);
		    	b8.setEnabled(false);
		    	b9.setEnabled(false);
		    	setWinner(winner);
		    	repaint();
		    	return;
		    }
		    else if(!boardNotFull(board)){
		    	repaint();
		    	return;
		    }

		    
			State state = new State(board, turn, 100, 0);
			State answerState = minimax(state);
			System.out.println("minimax: " + answerState.m);
			System.out.println("best move-> row: " + answerState.row + " col: " + answerState.col + " depth: " + answerState.depth);
		 	moveAI(answerState);
		  }
		};	

		b1.addActionListener(actionListener);
		b2.addActionListener(actionListener);
		b3.addActionListener(actionListener);
		b4.addActionListener(actionListener);
		b5.addActionListener(actionListener);
		b6.addActionListener(actionListener);
		b7.addActionListener(actionListener);
		b8.addActionListener(actionListener);
		b9.addActionListener(actionListener);

		/* start game */

		int whoseTurn;
		System.out.println("Enter who will be the first turn");
		System.out.println("1- Player \t 2-AI");
		Scanner scan = new Scanner(System.in);
		whoseTurn = scan.nextInt();
		if(whoseTurn == 2){
			State state = new State(board, turn, 100, 0);
			State answerState = minimax(state);
			System.out.println("minimax: " + answerState.m);
			System.out.println("best move-> row: " + answerState.row + " col: " + answerState.col + " depth: " + answerState.depth);
		 	moveAI(answerState);
		}	
						
	}  //constructor


/* methods */
	public void setBoard(int row, int col, String turn){
		this.board[row][col] = turn;
	}

	public void setWinner(String winner){
		this.winner = winner;
	}

	public void paint(Graphics graphics){
		super.paint(graphics);
		Graphics2D g = (Graphics2D)graphics;
		if(winner=="X" || winner == "O"){
			Font myFont1 = new Font ("Courier New", 1, 27);
       		g.setFont(myFont1);
       		g.drawString("Player " + this.winner + " won!!!", 100, 600); 
		}
		else if(!boardNotFull(board)){
			Font myFont1 = new Font ("Courier New", 1, 27);
       		g.setFont(myFont1);
       		g.drawString("DRAW!!", 100, 600); 
		}
	}


	public void moveAI(State state){
		int row = state.row;
		int col = state.col;

		if(turn == PRINTY)
			turn = PRINTX;
		else
			turn = PRINTY;

		this.board[row][col] = turn;
		if(row == 0 && col == 0){
			b1.setFont(new Font("Arial", Font.PLAIN, 90));
			b1.setText(turn);
			b1.setEnabled(false);
		}
		else if(row == 0 && col == 1){
			b2.setFont(new Font("Arial", Font.PLAIN, 90));
			b2.setText(turn);
			b2.setEnabled(false);
		}
		else if(row == 0 && col == 2){
			b3.setFont(new Font("Arial", Font.PLAIN, 90));
			b3.setText(turn);
			b3.setEnabled(false);
		}
		else if(row == 1 && col == 0){
			b4.setFont(new Font("Arial", Font.PLAIN, 90));
			b4.setText(turn);
			b4.setEnabled(false);
		}
		else if(row == 1 && col == 1){
			b5.setFont(new Font("Arial", Font.PLAIN, 90));
			b5.setText(turn);
			b5.setEnabled(false);
		}
		else if(row == 1 && col == 2){
			b6.setFont(new Font("Arial", Font.PLAIN, 90));
			b6.setText(turn);
			b6.setEnabled(false);
	 	}
	 	else if(row == 2 && col == 0){
	 		b7.setFont(new Font("Arial", Font.PLAIN, 90));
			b7.setText(turn);
			b7.setEnabled(false);	
	 	}
	 	else if(row == 2 && col == 1){
	 		b8.setFont(new Font("Arial", Font.PLAIN, 90));
	 		b8.setText(turn);
			b8.setEnabled(false);
	 	}
	 	else if(row == 2 && col == 2){
	 		b9.setFont(new Font("Arial", Font.PLAIN, 90));
		  	b9.setText(turn);
			b9.setEnabled(false);
		}

		String winner = checkBoardWinner();
	 	System.out.println("checking if has winner actionListener(hasWinner: " + hasWinner + ")");
	    if(winner == "X" || winner == "O"){
	   		b1.setEnabled(false);
		   	b2.setEnabled(false);
		  	b3.setEnabled(false);
		   	b4.setEnabled(false);
		   	b5.setEnabled(false);
		   	b6.setEnabled(false);
	    	b7.setEnabled(false);
		   	b8.setEnabled(false);
		   	b9.setEnabled(false);
		   	this.winner = winner;
			repaint();
	   		return;
	 	}
   		else if(!boardNotFull(board)){
		   	repaint();
		   	return;
		}

		if(this.turn == PRINTY)
			this.turn = PRINTX;
		else
			this.turn = PRINTY;
	}

	public State minimax(State state){
		// MAX === AI
		// MIN === player

		// kapag win +10, else -10
	  /* check 1st kung may nanalo na
		then saka icheck kung board full, 
		para di magrreturn kpg board full pero may nanalo na pala */

		/* for tracing */
		// if(this.noOfTurn == 3){
		// 	return 0;
		// }

		System.out.println("winner: " + checkHasWinner(state.board));

		if(checkHasWinner(state.board) == "X"){   // (terminal state) if in that state, nanalo si player, return -10
			state.setM(-1);
			System.out.println("returning state(terminal X won)-> row: " + state.row + " col: " + state.col);
			return state;  
		}
		if(checkHasWinner(state.board) == "O"){   // (terminal state) if in that state, nanalo si computer, return 10
			state.setM(1);
			System.out.println("returning state(terminal O won)-> row: " + state.row + " col: " + state.col);
			return state;  
		}
		if(!boardNotFull(state.board)){     // (terminal state) kapag draw
			state.setM(0);
			System.out.println("returning state(terminal draw)-> row: " + state.row + " col: " + state.col);
			return state;  

		}
 
		String stateTurn;

		if(state.turn == "X"){   //baliktad dapat kasi si state.turn ay ung 
			stateTurn = "O";
		}
		else{
			stateTurn = "X";
		}

		int m = (state.turn == "0" ? -100: 100);   // MAX si 0(ai)		

		ArrayList<State> possibleState = new ArrayList<State>();		
		
//tracing (mother state)
		System.out.println("=============================");
		state.printBoard();
		System.out.println("=============================");

//tracing the turn of the successor states
		System.out.println("current state turn: " + state.turn);
		System.out.println("stateTurn: " + stateTurn);

		if(state.turn == "X")   //baliktad dapat kasi si state.turn ay equal sa last turn na nilagay sakanya, hindi sa next turn
			System.out.println("max node: "); 
		else 
			System.out.println("min node: ");
		
		//get the possible states (successors)
		for(int i = 0; i < 3; i++){
			for(int j = 0 ; j < 3; j++){
				if(state.board[i][j] == null){
					State newState = new State(state.board, i, j ,stateTurn, m, state.depth+1);
					System.out.println("new state-> turn: " + newState.turn + " m: " + newState.m + " depth: " + newState.depth);
					newState.printBoard();
					System.out.println();
					possibleState.add(newState);
				}
			}
		}

		this.noOfTurn++;

		//ArrayList<State> max_valuePossibleState = new ArrayList<State>();
		
		// (max_value)get the value of each possible state 
		if(state.turn == "X"){  //if state is max node
			int maxM = -100;
			int score;
			State stateToReturn = null;

			for(int i = 0; i < possibleState.size(); i++){ 
				State currentState = possibleState.get(i);
				score = minimax(currentState).m;
				if(score>maxM){
					maxM = score;
					stateToReturn = currentState;
				}
				else{
					maxM  = maxM;
				}
				//maxM = (score>maxM? score:maxM);				
			}
			state.setM(maxM);
			System.out.println("max m: " + maxM);
			stateToReturn.incrementDepth();
			System.out.println("returning state(max)-> row: " + stateToReturn.row + " col: " + stateToReturn.col  + " depth: " + stateToReturn.depth);
			return stateToReturn;
		}
		// (min_value)
		else{	//if state is min node
			int minM = 100;
			int score;
			State stateToReturn = null;

			for(int i = 0; i < possibleState.size(); i++){ 
				State currentState = possibleState.get(i);
				score = minimax(currentState).m;
				if(score<minM){
					minM = score;
					stateToReturn = currentState;
				}
				else{
					minM  = minM;
				}
				// minM = (score<minM? score:minM);
			}
			state.setM(minM);
			System.out.println("min m: " +minM);
			stateToReturn.incrementDepth();	
			System.out.println("returning state(min)-> row: " + stateToReturn.row + " col: " + stateToReturn.col + " depth: " + stateToReturn.depth);
			return stateToReturn;	
		}


	}  //minimax


	public boolean boardNotFull(String[][] board){
		//check if there is null across the row col 
		for(int i = 0; i < 3; i++){
			for(int j = 0 ; j < 3; j++){
				if(board[i][j] == null)
					return true;
			}
		}
		System.out.println("board full");
		return false;
	}


	public String checkHasWinner(String[][] board){
		//check rows
		String winner = "none";
		for(int i = 0 ; i < 3; i ++){
			if((board[i][0] == "X" && board[i][1] == "X" && board[i][2] == "X") || (board[i][0] == "O" && board[i][1] == "O" && board[i][2] == "O")){
				if(board[i][0] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
				return winner;
			}
		}
		//check columns
		for(int i = 0 ; i < 3; i ++){
			if((board[0][i] == "X" && board[1][i] == "X" && board[2][i] == "X") || (board[0][i] == "O" && board[1][i] == "O" && board[2][i] == "O")){
				if(board[0][i] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
				return winner;
			}
		}
		//check diagonals
		if((board[0][0] == "X" && board[1][1] == "X" && board[2][2] == "X") || (board[0][0] == "O" && board[1][1] == "O" && board[2][2] == "O")){
			if(board[0][0] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
			return winner;
		}
		if((board[0][2] == "X" && board[1][1] == "X" && board[2][0] == "X") || (board[0][2] == "O" && board[1][1] == "O" && board[2][0] == "O")){
			if(board[0][2] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
			return winner;
		}
			
		return winner; // if no winner
	}


	public String checkBoardWinner(){
		String winner = "none";
		for(int i = 0 ; i < 3; i ++){
			if((this.board[i][0] == "X" && this.board[i][1] == "X" && this.board[i][2] == "X") || (this.board[i][0] == "O" && this.board[i][1] == "O" && this.board[i][2] == "O")){
				if(this.board[i][0] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
				return winner;
			}
		}
		//check columns
		for(int i = 0 ; i < 3; i ++){
			if((this.board[0][i] == "X" && this.board[1][i] == "X" && this.board[2][i] == "X") || (this.board[0][i] == "O" && this.board[1][i] == "O" && this.board[2][i] == "O")){
				if(this.board[0][i] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
				return winner;
			}
		}
		//check diagonals
		if((this.board[0][0] == "X" && this.board[1][1] == "X" && this.board[2][2] == "X") || (this.board[0][0] == "O" && this.board[1][1] == "O" && this.board[2][2] == "O")){
			if(board[0][0] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
			return winner;
		}
		if((this.board[0][2] == "X" && this.board[1][1] == "X" && this.board[2][0] == "X") || (this.board[0][2] == "O" && this.board[1][1] == "O" && this.board[2][0] == "O")){
			if(this.board[0][2] == "X")
					winner = PRINTX;
				else
					winner = PRINTY;
				// System.out.println("player " + winner + " won!");
			return winner;
		}
			
		return winner; // if no winner
	}




}  //class