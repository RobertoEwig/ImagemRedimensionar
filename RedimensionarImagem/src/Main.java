import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Main {

	static double proporcao = 0.6949;

	@SuppressWarnings("unused")
	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		for(int i=0; i<args.length; i++) {
			System.out.println(args[i]);
			if(args[i].charAt(0)=='%') {
				proporcao = Double.parseDouble(args[i].replace("%", ""));
				System.err.println("\nNova proporção: "+proporcao+"\n");
			}else {				
				File dir = new File("novos");
				
				dir.mkdir();
				
				File img = new File(args[i]);
				
				if(img.exists()==false) {
					System.err.println("\nImpossivel carregar a imagem "+args[i]);
					continue;
				}
						
				int nHeight;
				
				System.err.println("Redimensionando "+args[i]);
				
				BufferedImage imagem = ImageIO.read(img.getAbsoluteFile());
				
				nHeight = (int) (imagem.getWidth() / proporcao);
				
				BufferedImage nwImg = new BufferedImage(imagem.getWidth(), nHeight, imagem.getType());
				
				Graphics2D g_nImg = nwImg.createGraphics();
				g_nImg.drawImage(imagem, 0, 0, imagem.getWidth(), nHeight, null);
				
				File newImageFile = new File("novos/" + img.getName());
		        ImageIO.write(nwImg, "JPG", newImageFile);
		        
		        System.err.println("A imagem "+args[i]+" foi redimensionada com sucesso!\n");
			}			
		}
		
		System.err.println("\nPronto as imagens estão na pasta 'novos'!");
	}

}
