import java.io.*;
import java.util.*;

public class State{
	String[][] board;
	String turn;
	int m;
	int depth;    // depth is 0-indexing so starting depth = 0 
	int row;
	int col;

	public State(String[][] srcboard, String turn, int m, int depth){     //for the topmost state
		this.board = new String[3][3];
		copyBoard(srcboard);
		this.turn = turn;
		this.m = m;
		this.depth = depth;
	}

	public State(String[][] srcboard, int row, int col, String turn, int m, int depth){
		this.board = new String[3][3];
		copyBoard(srcboard);
		this.row = row;
		this.col = col;
		this.board[row][col] = turn;
		this.turn = turn;
		this.m = m;
		this.depth = depth;
	}

	public void copyBoard(String[][] srcBoard){
		for(int i = 0 ; i < 3; i ++){
			for(int j = 0 ; j < 3;  j++){	
				this.board[i][j] = srcBoard[i][j];    //copy the current config of board to this new state board
			}
		}
	}

	public void printBoard(){
		for(int i = 0 ; i < 3; i ++){
			for(int j = 0 ; j < 3;  j++){
				if(this.board[i][j] == null)
					System.out.print("-" + " ");
				else	
					System.out.print(this.board[i][j] + " ");
			}
			System.out.println();
		}
	}

	public void incrementDepth(){
		this.depth+=1;
	}
	public void setM(int m){
		this.m = m;
	}

}