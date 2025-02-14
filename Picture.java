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
		// sets red and green values to zero to only keep blue
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
		// sets everything to 255 - color to invert the colors
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
		// gray requires all RGB values to be the same - averages out
		// current values and sets each color to the average
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
	  
	  // goes through whole array
	  for (int i = 0; i < this.getHeight(); i += size) {
		  for (int j = 0; j < this.getWidth(); j += size) {
			  int sumRed = 0;
			  int sumGreen = 0;
			  int sumBlue = 0;
			  
			  // edge case # 1 - at the bottom, bottom most section doesn't 
			  // divide out but column index still remaining
			  if (i + size >= this.getHeight() && j + size < this.getWidth()) {
				  int count = 0; // keeps track of how many pixels are in the section
				  // creates a smaller section
				  // gets the sums
				  for (int k = i; k < this.getHeight(); k++) {
					  for (int l = j; l < j+size; l++) {
						  count++;
						  Pixel obj = pixels[k][l];
						  sumRed += obj.getRed();
						  sumGreen += obj.getGreen();
						  sumBlue += obj.getBlue();
					  }
				  }
				  
				  // puts the average values into each pixel in section
				  for (int k = i; k < this.getHeight(); k++) {
					  for (int l = j; l < j+size; l++) {
						 pixels[k][l].setRed(sumRed/count);
						 pixels[k][l].setGreen(sumGreen/count);
						 pixels[k][l].setBlue(sumBlue/count);
					  }
				  }
			  } 
			  // edge case # 2 - at the rightmost section, section doesn't 
			  // divide out but row index still remaining
			  else if (i + size < this.getHeight() && j + size >= this.getWidth()) {
				  int count = 0; // keeps track of how many pixels are in the section
				  // creates a smaller section
				  // gets the sums
				  for (int k = i; k < i+size; k++) {
					  for (int l = j; l < this.getWidth(); l++) {
						  count++;
						  Pixel obj = pixels[k][l];
						  sumRed += obj.getRed();
						  sumGreen += obj.getGreen();
						  sumBlue += obj.getBlue();
					  }
				  }
				  
				  // puts the average values into each pixel in section
				  for (int k = i; k < i+size; k++) {
					  for (int l = j; l < this.getWidth(); l++) {
						 pixels[k][l].setRed(sumRed/count);
						 pixels[k][l].setGreen(sumGreen/count);
						 pixels[k][l].setBlue(sumBlue/count);
					  }
				  }
			  }
			  // edge case # 3 - at the bottom right corner, both don't divide out
			  else if (i + size >= this.getHeight() && j + size >= this.getWidth()) {
				  int count = 0; // keeps track of how many pixels are in the section
				  // creates a smaller section
				  // gets the sums
				  for (int k = i; k < this.getHeight(); k++) {
					  for (int l = j; l < this.getWidth(); l++) {
						  count++;
						  Pixel obj = pixels[k][l];
						  sumRed += obj.getRed();
						  sumGreen += obj.getGreen();
						  sumBlue += obj.getBlue();
					  }
				  }
				  
				  // puts the average values into each pixel in section
				  for (int k = i; k < this.getHeight(); k++) {
					  for (int l = j; l < this.getWidth(); l++) {
						 pixels[k][l].setRed(sumRed/count);
						 pixels[k][l].setGreen(sumGreen/count);
						 pixels[k][l].setBlue(sumBlue/count);
					  }
				  }
			  }
			  // normal case
			  else {
				for (int k = 0; k < size; k++) {
				
				  // gets the sums
				  for (int l = 0; l < size; l++) {
					  Pixel obj = pixels[i+k][j+l];
					  sumRed += obj.getRed();
					  sumGreen += obj.getGreen();
					  sumBlue += obj.getBlue();
				  }
				}
			  
			    // puts the average values into each pixel in section
				for (int k = 0; k < size; k++) {
				  for (int l = 0; l < size; l++) {
					  Pixel obj = pixels[i+k][j+l];
					  obj.setRed(sumRed/(size*size));
					  obj.setGreen(sumGreen/(size*size));
					  obj.setBlue(sumBlue/(size*size));
				  }
				}
			}
		  }
	  }
  }
  
  /** Method that blurs the picture
	* @param size Blur size, greater is more blur
	* @return Blurred picture
	*/
  public Picture blur(int size)
  {
	Pixel[][] pixels = this.getPixels2D();
	Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D();
	
	// goes through all pixels
	for (int i = 0; i < this.getHeight(); i++) {
		for (int j = 0; j < this.getWidth(); j++) {
			// sums
			int sumRed = 0;
			int sumGreen = 0;
			int sumBlue = 0;
			int count = 0; // keeps track of how many pixels are in the section 
			
			// creates a small square with selected pixel in the center
			for (int k = i-(size/2); k < i+(size/2); k++) {
				for (int l = j-(size/2); l < j+(size/2); l++) {
					if (k >= 0 && k < this.getHeight() && l >= 0 && l < this.getWidth()) {
						count++; // increments counter
						Pixel obj = pixels[k][l];
						// gets the sums
						sumRed += obj.getRed();
						sumGreen += obj.getGreen();
						sumBlue += obj.getBlue();
					}
				}
			}
			
			// puts in the average value into the center pixel of new image
			resultPixels[i][j].setRed(sumRed/count);
			resultPixels[i][j].setGreen(sumGreen/count);
			resultPixels[i][j].setBlue(sumBlue/count);
		}
	}
	
	return result;
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
   public Picture enhance(int size)
   {
     Pixel[][] pixels = this.getPixels2D();
     Picture result = new Picture(pixels.length, pixels[0].length);
     Pixel[][] resultPixels = result.getPixels2D();
     
     // goes through all pixels
     for (int i = 0; i < this.getHeight(); i++) {
		for (int j = 0; j < this.getWidth(); j++) {
			// sums
			int sumRed = 0;
			int sumGreen = 0;
			int sumBlue = 0;
			int count = 0; // keeps track of how many pixels are in the section 
			
			for (int k = i-(size/2); k < i+(size/2); k++) {
				for (int l = j-(size/2); l < j+(size/2); l++) {
					if (k >= 0 && k < this.getHeight() && l >= 0 && l < this.getWidth()) {
						count++; // increments counter
						Pixel obj = pixels[k][l];
						// gets the sums
						sumRed += obj.getRed();
						sumGreen += obj.getGreen();
						sumBlue += obj.getBlue();
					}
				}
			}
			
			// puts in enhance characteristics into new image using average
			resultPixels[i][j].setRed(2*pixels[i][j].getRed() - sumRed/count);
			resultPixels[i][j].setBlue(2*pixels[i][j].getBlue() - sumBlue/count);
			resultPixels[i][j].setGreen(2*pixels[i][j].getGreen() - sumGreen/count);
		}
	}
	
	return result;
   }
  /** Swaps the left and right side of an image
   *  @return The modified picture with swapped sides
   */ 
  public Picture swapLeftRight() {
	 Pixel[][] pixels = this.getPixels2D();
     Picture result = new Picture(pixels.length, pixels[0].length);
     Pixel[][] resultPixels = result.getPixels2D();
     
     // goes through all the pixels
     for (int i = 0; i < this.getHeight(); i++) {
		for (int j = 0; j < this.getWidth(); j++) {
			// computes new column number and puts in the old pixel into that column
			Pixel resultPixel = resultPixels[i][(j+this.getWidth()/2) % this.getWidth()];
			resultPixel.setColor(pixels[i][j].getColor());
		}
	 }
	 
	 // returns modified picture
	 return result;
  }
  
  /** Creates a jagged picture by shifting pixels a number of columns to the
    * right based on the row number and the width of the "step"
    * 
	* @param shiftCount The number of pixels to shift to the right
	* @param steps The number of steps
	* @return The picture with pixels shifted in stair steps
	*/
  public Picture stairStep(int shiftCount, int steps) {
	 Pixel[][] pixels = this.getPixels2D();
     Picture result = new Picture(pixels.length, pixels[0].length);
     Pixel[][] resultPixels = result.getPixels2D();
     
     // goes through all the pixels
     for (int i = 0; i < this.getHeight(); i++) {
		 // computes the right shift
		 int shift = (i/(this.getHeight()/steps))*shiftCount;
		 for (int j = 0; j < this.getWidth(); j++) {
			 // computes the new column based on right shift, uses mod to cap overflow
			 int newColumn = (j+shift)%this.getWidth();
			 // puts in the old pixel into new column
			 resultPixels[i][newColumn].setColor(pixels[i][j].getColor());
		 }
	 }
	 
	 // returns modified picture
	 return result;
  }
  
  /** Distorts the horizontal center of an image by shifting pixels horizontally
    * based on a Gaussian curve
    * 
	* @param maxFactor Max height (shift) of curve in pixels
	* @return Liquified picture
	*/
  public Picture liquify(int maxHeight) {
	 Pixel[][] pixels = this.getPixels2D();
     Picture result = new Picture(pixels.length, pixels[0].length);
     Pixel[][] resultPixels = result.getPixels2D();
     
     // sets the width of the bell in the bell curve
     int bellWidth = 100;
     // goes through all the pixels
     for (int i = 0; i < this.getHeight(); i++) {
		for (int j = 0; j < this.getWidth(); j++) {
			// computes exponent
			double exponent = Math.pow(i - this.getHeight()/2.0, 2)/(2.0*Math.pow(bellWidth, 2));
			// computes right shift for each pixel
			int rightShift = (int)(maxHeight * Math.exp(- exponent));
			
			// computes new column, caps overflow
			int newColumn = (j+rightShift)%this.getWidth();
			// puts old pixel into new column
			resultPixels[i][newColumn].setColor(pixels[i][j].getColor());
		}
	 }
	 
	 // returns modified picture
	 return result;
  }
  
  /** Creates oscillating distortions in a picture based on a sine curve
	* @param amplitude The maximum shift of pixels
	* @return Wavy picture
	*/
  public Picture wavy(int amplitude) {
	 Pixel[][] pixels = this.getPixels2D();
     Picture result = new Picture(pixels.length, pixels[0].length);
     Pixel[][] resultPixels = result.getPixels2D();
     
     double frequency = 0.01; // can change amplitude
     // goes through all pixels
     for (int i = 0; i < this.getHeight(); i++) {
		// computes shift for each row
		int shift = (int)(amplitude * Math.sin(2*Math.PI*frequency*i));
		for (int j = 0; j < this.getWidth(); j++) {
			// computes new column
			int newColumn = ((((j+shift)%this.getWidth())+this.getWidth())%this.getWidth());
			// puts old pixel into new column
			resultPixels[i][newColumn].setColor(pixels[i][j].getColor());
		}
	 }
	 
	 // returns modified picture
	 return result;
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
  
  /** Method that creates an edge detected black/white picture
	* @param threshold threshold as determined by Pixel’s colorDistance method
	* @return edge detected picture
	*/
  public Picture edgeDetectionBelow(int edgeDist)
  {
    Pixel upPixel = null;
    Pixel downPixel = null;
    Pixel[][] pixels = this.getPixels2D();
    Picture result = new Picture(pixels.length, pixels[0].length);
	Pixel[][] resultPixels = result.getPixels2D(); 
    Color downColor = null;
    
    for (int col = 0; col < pixels[0].length; col++)
    {
      for (int row = 0; 
           row < pixels.length-1; row++)
      {
        upPixel = pixels[row][col];
        downPixel = pixels[row+1][col];
        downColor = downPixel.getColor();
        if (upPixel.colorDistance(downColor) > 
            edgeDist)
          resultPixels[row][col].setColor(Color.BLACK);
        else
          resultPixels[row][col].setColor(Color.WHITE);
      }
    }
    
    return result;
  }
  
  /** Method that creates a green screen picture
	* @return green screen picture
	*/
  public Picture greenScreen()
  {
	// Get background picture
	Picture bkgnd = new Picture("greenScreenImages/IndoorHouseLibraryBackground.jpg");
	Pixel[][] bkgndPixels = bkgnd.getPixels2D();
	// Get minion1 picture
	Picture minion1 = new Picture("greenScreenImages/minion1GreenScreen.jpg");
	Pixel[][] minion1Pixels = minion1.getPixels2D();
	minion1 = minion1.scale(0.7, 0.7);
	// Get minion2 picture
	Picture minion2 = new Picture("greenScreenImages/minion2GreenScreen.jpg");
	Pixel[][] minion2Pixels = minion2.getPixels2D(); 
	minion2 = minion2.scale(0.4, 0.4);
	
	return bkgnd;
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
