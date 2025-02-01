public class IntArrayWorker
{
  /** two dimensional matrix */
  private int[][] matrix = null;
  
  /** set the matrix to the passed one
    * @param theMatrix the one to use
    */
  public void setMatrix(int[][] theMatrix)
  {
    matrix = theMatrix;
  }
  
  /**
   * Method to return the total 
   * @return the total of the values in the array
   */
  public int getTotal()
  {
    int total = 0;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        total = total + matrix[row][col];
      }
    }
    return total;
  }
  
  public int getCount(int value) {
	  int count = 0;
	  
	  for (int i = 0; i < matrix.length; i++) {
		  for (int j = 0; j < matrix[i].length; j++) {
			  if (matrix[i][j] == value) {
				  count++;
			  }
		  }
	  }
	  
	  return count;
  }
  
  public int getLargest () {
	  int max = 0;
	  
	  for (int i = 0; i < matrix.length; i++) {
		  for (int j = 0; j < matrix[i].length; j++) {
			  if (matrix[i][j] > max) {
				  max = matrix[i][j];
			  }
		  }
	  }
	  
	  return max;
  }
  
  public int getColTotal(int column) {
	  int sum = 0;
	  for(int i = 0; i < matrix.length; i++) {
		  sum += matrix[i][column];
	  }
	  
	  return sum;
  }
  
  public int[][] reverseRows (int[][] original) {
	  for (int i = 0; i < original.length; i++) {
		  for (int j = 0; j < original[i].length/2; j++) {
			  int t = original[i][j];
			  original[i][j] = original[i][original[i].length - 1 - j];
			  original[i][original[i].length - 1 - j] = t;
		  }
	  }
	  
	  return original;
  }
  
  /**
   * Method to return the total using a nested for-each loop
   * @return the total of the values in the array
   */
  public int getTotalNested()
  {
    int total = 0;
    for (int[] rowArray : matrix)
    {
      for (int item : rowArray)
      {
        total = total + item;
      }
    }
    return total;
  }
  
  /**
   * Method to fill with an increasing count
   */
  public void fillCount()
  {
    int numCols = matrix[0].length;
    int count = 1;
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < numCols; col++)
      {
        matrix[row][col] = count;
        count++;
      }
    }
  }
  
  /**
   * print the values in the array in rows and columns
   */
  public void print()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; col++)
      {
        System.out.print( matrix[row][col] + " " );
      }
      System.out.println();
    }
    System.out.println();
  }
  
  
  /** 
   * fill the array with a pattern
   */
  public void fillPattern1()
  {
    for (int row = 0; row < matrix.length; row++)
    {
      for (int col = 0; col < matrix[0].length; 
           col++)
      {
        if (row < col)
          matrix[row][col] = 1;
        else if (row == col)
          matrix[row][col] = 2;
        else
          matrix[row][col] = 3;
      }
    }
  }
 
}
