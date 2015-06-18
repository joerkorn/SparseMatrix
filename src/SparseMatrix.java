import java.util.ArrayList;


public class SparseMatrix<anyType> {
	
	private ArrayList<Cell> matrix;
	private int rows, cols;
	public SparseMatrix(int r, int c) {
		matrix = new ArrayList<Cell>();
		rows = r;
		cols = c;
	}
	public anyType get(int r, int c) {
		for(int i=0; i<matrix.size(); i++)
			if(this.matrix.get(i).getRow() == r && this.matrix.get(i).getCol() == c)
				return (anyType) matrix.get(i).getVal();
		return null;
	}
	public void add(int r, int c, anyType v) {
		int i = 0;
		while((matrix.size() > 0) && (i<matrix.size()) && (matrix.get(i).getKey() > (r * this.cols + 1)))
			i++;
		matrix.add(new Cell(r, c, v));
		matrix.get(i).setKey(r * cols + c);
	}
	public anyType set(int r, int c, anyType v) {
		Cell temp;
		for(int i=0; i<matrix.size(); i++)
			if(this.matrix.get(i).getRow() == r && this.matrix.get(i).getCol() == c) {
				temp = matrix.get(i);
				matrix.set(i, new Cell(r, c, v));
				return (anyType) temp.getVal();
			}
		return null;
	}
	public anyType remove(int r, int c) {
		Cell temp;
		for(int i=0; i<matrix.size(); i++)
			if(this.matrix.get(i).getRow() == r && this.matrix.get(i).getCol() == c) {
				temp = matrix.get(i);
				matrix.remove(i);
				return (anyType) temp.getVal();
			}
		return null;
	}
	public boolean contains(int r, int c) {
		for(int i=0; i<matrix.size(); i++)
			if(this.matrix.get(i).getRow() == r && this.matrix.get(i).getCol() == c)
				return true;
		return false;
	}
	public void setKeys() {
		int maxCol = this.cols;
		for(int i=0; i<matrix.size(); i++)
			matrix.get(i).setKey(matrix.get(i).getRow() * this.cols + matrix.get(i).getCol());
	}
	@Override
	public String toString() {
		String str = "";
		for(int r=0; r<this.rows; r++) {
			str += "\n";
			for(int c=0; c<this.cols; c++) {
				if(this.contains(r, c))
					str+= this.get(r, c).toString();
				else
					str+= "-";
			}
		}
		return str;
	}
}
