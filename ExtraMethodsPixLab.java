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
