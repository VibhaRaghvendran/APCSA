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
