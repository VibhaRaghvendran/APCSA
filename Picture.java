import java.awt.*;
import java.awt.font.*;
import java.awt.geom.*;
import java.awt.image.BufferedImage;
import java.text.*;
import java.util.*;
import java.util.List; // resolves problem with java.awt.List and java.util.List

/**
 * A class that represents a picture.  This class inherits from 
 * SimplePicture and allows the student to add functionality to
 * the Picture class.  
 * 
 * @author Barbara Ericson ericson@cc.gatech.edu
 */
public class Picture extends SimplePicture 
{
  ///////////////////// constructors //////////////////////////////////
  
  /**
   * Constructor that takes no arguments 
   */
  public Picture ()
  {
    /* not needed but use it to show students the implicit call to super()
     * child constructors always call a parent constructor 
     */
    super();  
  }
  
  /**
   * Constructor that takes a file name and creates the picture 
   * @param fileName the name of the file to create the picture from
   */
  public Picture(String fileName)
  {
    // let the parent class handle this fileName
    super(fileName);
  }
  
  /**
   * Constructor that takes the width and height
   * @param height the height of the desired picture
   * @param width the width of the desired picture
   */
  public Picture(int height, int width)
  {
    // let the parent class handle this width and height
    super(width,height);
  }
  
  /**
   * Constructor that takes a picture and creates a 
   * copy of that picture
   * @param copyPicture the picture to copy
   */
  public Picture(Picture copyPicture)
  {
    // let the parent class do the copy
    super(copyPicture);
  }
  
  /**
   * Constructor that takes a buffered image
   * @param image the buffered image to use
   */
  public Picture(BufferedImage image)
  {
    super(image);
  }
  
  ////////////////////// methods ///////////////////////////////////////
  
  /**
   * Method to return a string with information about this picture.
   * @return a string with information about the picture such as fileName,
   * height and width.
   */
  public String toString()
  {
    String output = "Picture, filename " + getFileName() + 
      " height " + getHeight() 
      + " width " + getWidth();
    return output;
    
  }
  
  /** Method to set the blue to 0 */
  public void zeroBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setBlue(0);
      }
    }
  }
  
   /** Method to keep only blue */
  public void keepOnlyBlue()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(0);
        pixelObj.setGreen(0);
      }
    }
  }
  
  /** Method to negate colors */
  public void negate()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        pixelObj.setRed(255-pixelObj.getRed());
        pixelObj.setGreen(255-pixelObj.getGreen());
        pixelObj.setBlue(255-pixelObj.getBlue());
      }
    }
  }
  
  /** Method to turn grayscale */
  public void grayscale()
  {
    Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        int avg = (pixelObj.getRed() + pixelObj.getGreen() + pixelObj.getBlue())/3;
        pixelObj.setRed(avg);
        pixelObj.setGreen(avg);
        pixelObj.setBlue(avg);
      }
    }
  }
  
  /** Modifies fish to make them more visible */
  public void fixUnderwater() 
  {
	Pixel[][] pixels = this.getPixels2D();
    for (Pixel[] rowArray : pixels)
    {
      for (Pixel pixelObj : rowArray)
      {
        if (pixelObj.getBlue() > 150 && pixelObj.getGreen() < 170 && pixelObj.getRed() <= 25) {
			pixelObj.setBlue(pixelObj.getBlue() + 40);
			pixelObj.setRed(pixelObj.getRed() + 25);
		}
		else {
			pixelObj.setGreen(pixelObj.getGreen() - 10);
			pixelObj.setBlue(pixelObj.getBlue() - 10);
		}
      }
    }
  }
  
  /** Method to watermark images */
  public void watermark()
  {
	boolean hasGridX = true;
	boolean hasGridY = true;
    Pixel[][] pixels = this.getPixels2D();
    for (int i = 0; i < pixels.length; i++) {
		for (int j = 0; j < pixels[i].length; j++) {
			if (i%10 == 0) {
				if (hasGridX == true) {
					hasGridX = false;
				}
				else {
					hasGridX = true; 
				}
			}
			if (j%10 == 0) {
				if (hasGridY == true) {
					hasGridY = false;
				}
				else {
					hasGridY = true; 
				}
			}
			if (hasGridX == false || hasGridY == false) {
				pixels[i][j].setBlue(pixels[i][j].getBlue() - 10);
				pixels[i][j].setGreen(pixels[i][j].getGreen() - 10);
				pixels[i][j].setRed(pixels[i][j].getRed() - 10);
			}
		}
	}
  }
  
  /** To pixelate by dividing area into size x size.
	* @param size Side length of square area to pixelate.
	*/
  public void pixelate(int size) {
	  Pixel[][] pixels = this.getPixels2D();
	  int maxX = pixels.length/size;
	  int maxY = pixels[0].length/size;
	  int sumRed = 0;
	  int sumGreen = 0;
	  int sumBlue = 0;
	  for (int i = 0; i < (maxX-1)*size; i+=size) {
		  for (int j = 0; j < (maxY-1)*size; j+=size) { 
			  for (int k = i; k < i+size; k++) {
				  for (int l = j; l < j+size; l++) {
					  sumRed += pixels[k][l].getRed();
					  sumGreen += pixels[k][l].getGreen();
					  sumBlue += pixels[k][l].getBlue(); 
				  }
			  }
			  
			  int red = sumRed/(size*size);
			  int green = sumGreen/(size*size);
			  int blue = sumBlue/(size*size);
			  
			  for (int k = i; k < i+size; k++) {
				  for (int l = j; l < j+size; l++) {
					  pixels[k][l].setRed(red); 
					  pixels[k][l].setGreen(green);
					  pixels[k][l].setBlue(blue);
				  }
			  }
			  sumRed = 0;
		      sumGreen = 0;
	          sumBlue = 0;
		  }
	  }
	  
	  if (maxX*size < pixels.length) {
		  for (int j = 0; j < pixels[0].length; j+=size) {
			  for (int k = maxX*size; k < pixels.length; k++) {
				  for (int l = j; l < j+size; l++) {
					  sumRed += pixels[k][l].getRed();
					  sumGreen += pixels[k][l].getGreen();
					  sumBlue += pixels[k][l].getBlue(); 
				  }
			  }
			  
			  for (int k = maxX*size; k < pixels.length; k++) {
				  for (int l = j; l < j+size; l++) {
					  pixels[k][l].setRed((sumRed)/(size^2)); 
					  pixels[k][l].setGreen((sumGreen)/(size^2));
					  pixels[k][l].setBlue((sumBlue)/(size^2));
				  }
			  }
			  
			  sumRed = 0;
		      sumGreen = 0;
	          sumBlue = 0;
		  }
	  }
	  
	  if (maxY*size < pixels[0].length) {
		  for (int j = 0; j < pixels.length; j+=size) {
			
			  for (int k = maxY*size; k < pixels[0].length; k++) {
				  for (int l = j; l < j+size; l++) {
					  sumRed += pixels[k][l].getRed();
					  sumGreen += pixels[k][l].getGreen();
					  sumBlue += pixels[k][l].getBlue(); 
				  }
			  }
			  
			  for (int k = maxY*size; k < pixels[0].length; k++) {
				  for (int l = j; l < j+size; l++) {
					  pixels[k][l].setRed((sumRed)/(size^2)); 
					  pixels[k][l].setGreen((sumGreen)/(size^2));
					  pixels[k][l].setBlue((sumBlue)/(size^2));
				  }
			  }
			  
			  sumRed = 0;
		      sumGreen = 0;
	          sumBlue = 0;
		  }
	  }
  }
  
  /** Method that blurs the picture
	* @param size Blur size, greater is more blur
	* @return Blurred picture
	*/
  public void blur(int size)
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	
  } 
  
  /** Method that enhances a picture by getting average Color around
	* a pixel then applies the following formula:
	*
	* pixelColor <- 2 * currentValue - averageValue
	*
	* size is the area to sample for blur.
	*
	* @param size Larger means more area to average around pixel
	* and longer compute time.
	* @return enhanced picture
	*/
   public void enhance(int size)
   {
     Pixel[][] pixels = this.getPixels2D();
     Picture result = new Picture(pixels.length, pixels[0].length);
     Pixel[][] resultPixels = result.getPixels2D();
     
   }
  
  /** Method that mirrors the picture around a 
    * vertical mirror in the center of the picture
    * from left to right */
  public void mirrorVertical()
  {
    Pixel[][] pixels = this.getPixels2D();
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int width = pixels[0].length;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; col < width / 2; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][width - 1 - col];
        rightPixel.setColor(leftPixel.getColor());
      }
    } 
  }
  
  /** Mirror just part of a picture of a temple */
  public void mirrorTemple()
  {
    int mirrorPoint = 276;
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    int count = 0;
    Pixel[][] pixels = this.getPixels2D();
    
    // loop through the rows
    for (int row = 27; row < 97; row++)
    {
      // loop from 13 to just before the mirror point
      for (int col = 13; col < mirrorPoint; col++)
      {
        
        leftPixel = pixels[row][col];      
        rightPixel = pixels[row]                       
                         [mirrorPoint - col + mirrorPoint];
        rightPixel.setColor(leftPixel.getColor());
      }
    }
  }
  
  /** copy from the passed fromPic to the
    * specified startRow and startCol in the
    * current picture
    * @param fromPic the picture to copy from
    * @param startRow the start row to copy to
    * @param startCol the start col to copy to
    */
  public void copy(Picture fromPic, 
                 int startRow, int startCol)
  {
    Pixel fromPixel = null;
    Pixel toPixel = null;
    Pixel[][] toPixels = this.getPixels2D();
    Pixel[][] fromPixels = fromPic.getPixels2D();
    for (int fromRow = 0, toRow = startRow; 
         fromRow < fromPixels.length &&
         toRow < toPixels.length; 
         fromRow++, toRow++)
    {
      for (int fromCol = 0, toCol = startCol; 
           fromCol < fromPixels[0].length &&
           toCol < toPixels[0].length;  
           fromCol++, toCol++)
      {
        fromPixel = fromPixels[fromRow][fromCol];
        toPixel = toPixels[toRow][toCol];
        toPixel.setColor(fromPixel.getColor());
      }
    }   
  }

  /** Method to create a collage of several pictures */
  public void createCollage()
  {
    Picture flower1 = new Picture("images/flower1.jpg");
    Picture flower2 = new Picture("images/flower2.jpg");
    this.copy(flower1,0,0);
    this.copy(flower2,100,0);
    this.copy(flower1,200,0);
    Picture flowerNoBlue = new Picture(flower2);
    flowerNoBlue.zeroBlue();
    this.copy(flowerNoBlue,300,0);
    this.copy(flower1,400,0);
    this.copy(flower2,500,0);
    this.mirrorVertical();
    this.write("images/collage.jpg");
  }
  
  
  /** Method to show large changes in color 
    * @param edgeDist the distance for finding edges
    */
  public void edgeDetection(int edgeDist)
  {
    Pixel leftPixel = null;
    Pixel rightPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Color rightColor = null;
    for (int row = 0; row < pixels.length; row++)
    {
      for (int col = 0; 
           col < pixels[0].length-1; col++)
      {
        leftPixel = pixels[row][col];
        rightPixel = pixels[row][col+1];
        rightColor = rightPixel.getColor();
        if (leftPixel.colorDistance(rightColor) > 
            edgeDist)
          leftPixel.setColor(Color.BLACK);
        else
          leftPixel.setColor(Color.WHITE);
      }
    }
  }
  
  
  /* Main method for testing - each class in Java can have a main 
   * method 
   */
  public static void main(String[] args) 
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
} // this } is the end of class Picture, put all new methods before this
