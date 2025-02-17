package piece;

import main.GamePanel;
import main.Type;

public class King extends Piece{

	
	public King(int color, int col, int row) {
		super(color, col, row);
		
		type = Type.KING;
		
		if(color == GamePanel.WHITE) {
			image = getImage("/piece/w-king");
		}
		else {
			image = getImage("/piece/b-king");
		}
	}
	
	public boolean canMove(int targetCol, int targetRow) {
		
		if(isWithinBoard(targetCol, targetRow)) {
			
			//Movement
			if(Math.abs(targetCol - preCol) + Math.abs(targetRow - preRow) == 1 ||
					(Math.abs(targetCol - preCol) * Math.abs(targetRow - preRow) == 1)) {
				
				if(isValidSquare(targetCol, targetRow)) {
					return true;
				}
			}
			
			//Castling
			if(moved == false) {
				
				//Right castling
				if(targetCol == preCol+2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false) {
					Piece pR[] = new Piece[2];
					for(Piece piece : GamePanel.simPieces) {
						//Check if there is knight
						if(piece.col == preCol+2 && piece.row == preRow && piece.type != Type.KING) {
							pR[0] = piece;
						}
						if(piece.col == preCol+3 && piece.row == preRow){
							pR[1] = piece;
						}
					}
					System.out.println(pR[0]);
					System.out.println(pR[1]);
					if(pR[0] == null && pR[1] != null && pR[1].moved == false) {
						GamePanel.castlingP = pR[1];
						return true;
					}
				}
				//Left castling
				if(targetCol == preCol-2 && targetRow == preRow && pieceIsOnStraightLine(targetCol, targetRow) == false) {
					Piece pL[] = new Piece[2];
					for(Piece piece : GamePanel.simPieces) {
						//Check if there is knight
						if(piece.col == preCol-3 && piece.row == preRow) {
							pL[0] = piece;
						}
						//Check if there is rook
						if(piece.col == preCol-4 && piece.row == preRow) {
							pL[1] = piece;
						}
						System.out.println(pL[0]);
						System.out.println(pL[1]);
					}
					if(pL[0] == null && pL[1] != null && pL[1].moved == false) {
						GamePanel.castlingP = pL[1];
						return true;
					}
				}
			}
		}
		return false;
	}

}
