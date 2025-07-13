package local.ytk.util.awt;

import java.awt.*;

public class GraphicsUtil {
    public static void drawCenteredString(Graphics g, String text, int x, int y, int width, int height) {
        FontMetrics metrics = g.getFontMetrics(g.getFont());
        int textWidth = metrics.stringWidth(text);
        int textHeight = metrics.getHeight();
        
        int drawX = x + (width - textWidth) / 2;
        int drawY = y + (height - textHeight) / 2 + metrics.getAscent();
        
        g.drawString(text, drawX, drawY);
    }
    
    public static void drawCenteredString(Graphics g, String text, Rectangle rect) {
        drawCenteredString(g, text, rect.x, rect.y, rect.width, rect.height);
    }
}
