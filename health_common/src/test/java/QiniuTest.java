import com.bigdata.utils.QiniuUtils;
import org.junit.Test;

/**
 * @ClassName: QiniuTest
 * @Description
 * @Author:我自己
 * @Date: 2022/5/27  18:46
 * @Version 1.0
 */
public class QiniuTest {
    @Test
    public void testUpload(){
        String localFilePath="D:\\picture\\01.jpg";
        QiniuUtils.upload2Qiniu(localFilePath, "01.jpg");

    }
    @Test
    public void testDeleteFile(){
        QiniuUtils.deleteFileFromQiniu("01.jpg");
    }
}
