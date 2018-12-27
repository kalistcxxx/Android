package com.corp.k.androidos.android.models.enums;


/**
 * Created by hoangtuan on 1/23/17.
 */
public enum FileTypeCode {
    non(0),
    gif(1),
    pdf(4),
    jpg(5),
    jpeg(6),
    png(7),
    bmp(8),
    tif(9),
    tiff(10),
    txt(11),
    xls(12),
    xlsx(13),
    doc(14),
    docx(15),
    ppt(16),
    pptx(17),
    NotApplicable(19);

    private int value;

    FileTypeCode(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public static FileTypeCode fromInteger(int id){
        FileTypeCode[] values = FileTypeCode.values();
        for (FileTypeCode v : values) {
            if (v.getValue() == id)
                return v;
        }
        return null;
    }

//    @Override
//    public String toString() {
//        if (value > 0)
//            return CommonUtils.getResourceStringByKey(AppConstants.FILE_TYPE_PREFIX + value);
//        else
//            return CommonUtils.getResourceStringByKey(AppConstants.FILE_TYPE_PREFIX + FileTypeCode.non.getValue());
//    }
}
