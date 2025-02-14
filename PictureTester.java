/**
 * This class contains class (static) methods
 * that will help you test the Picture class 
 * methods.  Uncomment the methods and the code
 * in the main to test.
 * 
 * @author Barbara Ericson 
 */
public class PictureTester
{
  /** Method to test zeroBlue */
  public static void testZeroBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.zeroBlue();
    beach.explore();
  }
  
  /** Method to test keepOnlyBlue */
  public static void testKeepOnlyBlue()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.keepOnlyBlue();
    beach.explore();
  }
  
  /** Method to test negate */
  public static void testNegate()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.negate();
    beach.explore();
  }
  
  /** Method to test grayscale */
  public static void testGrayscale()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.grayscale();
    beach.explore();
  }
  
  /** Method to test pixelate */
  public static void testPixelate()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach.pixelate(11);
    beach.explore();
  }
  
  /** Method to test blur */
  public static void testBlur()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.blur(11);
    beach.explore();
  }
  
  /** Method to test blur */
  public static void testEnhance()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.enhance(11);
    beach.explore();
  }
  
  /** Method to test swapLeftRight */
  public static void testSwapLeftRight()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.swapLeftRight();
    beach.explore();
  }
  
  /** Method to test stairStep */
  public static void testStairStep()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.stairStep(50, 15);
    beach.explore();
  }
  
  /** Method to test liquify */
  public static void testLiquify()
  {
    Picture beach = new Picture("images/beach.jpg");
    beach.explore();
    beach = beach.liquify(250);
    beach.explore();
  }
  
  /** Method to test wavy */
  public static void testWavy()
  {
    Picture beach = new Picture("images/redMotorcycle.jpg");
    beach.explore();
    beach = beach.wavy(25);
    beach.explore();
  }
  
  /** Method to test mirrorVertical */
  public static void testMirrorVertical()
  {
    Picture caterpillar = new Picture("caterpillar.jpg");
    caterpillar.explore();
    caterpillar.mirrorVertical();
    caterpillar.explore();
  }
  
  /** Method to test mirrorTemple */
  public static void testMirrorTemple()
  {
    Picture temple = new Picture("temple.jpg");
    temple.explore();
    temple.mirrorTemple();
    temple.explore();
  }
  
  /** Method to test the collage method */
  public static void testCollage()
  {
    Picture canvas = new Picture("640x480.jpg");
    canvas.createCollage();
    canvas.explore();
  }
  
  /** Method to test edgeDetection */
  public static void testEdgeDetection()
  {
    Picture swan = new Picture("swan.jpg");
    swan.edgeDetection(10);
    swan.explore();
  }
  
  /** Method to test edgeDetectionBelow */
  public static void testEdgeDetectionBelow()
  {
    Picture swan = new Picture("images/swan.jpg");
    swan.explore();
    swan = swan.edgeDetectionBelow(10);
    swan.explore();
  }
  
  /** Method to test greenScreen */
  public static void testGreenScreen()
  {
	Picture minion1 = new Picture("greenScreenImages/minion1GreenScreen.jpg");
	minion1.explore();
	Picture minion2 = new Picture("greenScreenImages/minion2GreenScreen.jpg");
	minion2.explore();
	// choose any picture to start since it will *not* be used
	//Picture pic = new Picture("images/beach.jpg");
	//Picture gScreen = pic.greenScreen();
	//gScreen.explore();
  } 
  
  /** Main method for testing.  Every class can have a main
    * method in Java */
  public static void main(String[] args)
  {
    // uncomment a call here to run a test
    // and comment out the ones you don't want
    // to run
    //testZeroBlue();
    //testKeepOnlyBlue();
    //testKeepOnlyRed();
    //testKeepOnlyGreen();
    //testNegate();
    //testGrayscale();
    //testFixUnderwater();
    testPixelate();
    //testBlur();
    //testEnhance();
    //testSwapLeftRight();
    //testStairStep();
    //testLiquify();
    //testWavy();
    //testMirrorVertical();
    //testMirrorTemple();
    //testMirrorArms();
    //testMirrorGull();
    //testMirrorDiagonal();
    //testCollage();
    //testCopy();
    //testEdgeDetection();
    //testEdgeDetectionBelow();
    //testGreenScreen();
    //testChromakey();
    //testEncodeAndDecode();
    //testGetCountRedOverValue(250);
    //testSetRedToHalfValueInTopHalf();
    //testClearBlueOverValue(200);
    //testGetAverageForColumn(0);
  }
}
