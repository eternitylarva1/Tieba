package Zhenghuo.utils;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.TextureData;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Pixmap.Format;
import com.esotericsoftware.spine.Slot;
import com.esotericsoftware.spine.attachments.Attachment;
import com.esotericsoftware.spine.attachments.MeshAttachment;
import com.esotericsoftware.spine.attachments.RegionAttachment;

public class SpineRegionExtractor {

    /**
     * 从指定的 Slot 中获取 TextureRegion
     * @param slot 要处理的 Slot 对象
     * @return 对应的 TextureRegion，如果不存在则返回 null
     */
    public static TextureRegion getTextureRegionFromSlot(Slot slot) {
        if (slot == null) {
            return null;
        }
        // 从 Slot 中获取 Attachment
        Attachment attachment = slot.getAttachment();
        if (attachment instanceof RegionAttachment) {
            // 获取 TextureRegion
            RegionAttachment regionAttachment = (RegionAttachment) attachment;
            return regionAttachment.getRegion();
        } else if (attachment instanceof MeshAttachment) {
            MeshAttachment meshAttachment = (MeshAttachment) attachment;
            return meshAttachment.getRegion();
        }
        return null;
    }

    /**
     * 从指定的 Slot 中获取 Texture
     * @param slot 要处理的 Slot 对象
     * @return 对应的 Texture，如果不存在则返回 null
     */
    public static Texture getTextureFromSlot(Slot slot) {
        TextureRegion region = getTextureRegionFromSlot(slot);
        if (region != null) {
            return region.getTexture();
        }
        return null;
    }

    /**
     * 将纹理缩放到长度小于 64 像素
     * @param texture 要缩放的纹理
     * @return 缩放后的纹理
     */
    public static Texture scaleTextureToUnder64(Texture texture) {
        if (texture == null) {
            return null;
        }
        int width = texture.getWidth();
        int height = texture.getHeight();
        int maxLength = Math.max(width, height);

        if (maxLength < 64) {
            return texture; // 如果纹理已经小于 64 像素，直接返回
        }

        float scaleFactor = 64f / maxLength;
        int newWidth = (int) (width * scaleFactor);
        int newHeight = (int) (height * scaleFactor);

        // 获取纹理数据并确保其已准备好
        TextureData textureData = texture.getTextureData();
        textureData.prepare();
        Pixmap srcPixmap = textureData.consumePixmap();

        Pixmap scaledPixmap = new Pixmap(newWidth, newHeight, Format.RGBA8888);
        scaledPixmap.drawPixmap(srcPixmap, 0, 0, width, height, 0, 0, newWidth, newHeight);

        // 如果 consumePixmap 返回的是一个新的 Pixmap 实例，则释放它
        if (textureData.getType() !=TextureData.TextureDataType.Custom) {
            srcPixmap.dispose();
        }

        // 创建一个新的纹理
        Texture scaledTexture = new Texture(scaledPixmap);
        scaledPixmap.dispose();

        return scaledTexture;
    }
}