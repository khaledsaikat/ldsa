package de.due.ldsa.db.model;

/**
 * Created by  Romina
 * Creation Timestamp 16.01.2016 15:02
 */
public enum Filetyp {

    GraphicsInterchangeFormat(TypeClass.StaticImage),
    JPEG(TypeClass.StaticImage),
    PortableNetworkGraphic(TypeClass.StaticImage),
    WebM(TypeClass.Video),
    MicrosoftWAVE(TypeClass.Audio),
    /**
     * MP4 files are described at Part 14 of the ISO-14496 specification.
     */
    ISO14496Part14(TypeClass.Video),
    Unknown(TypeClass.Unknown),
    MicrosoftBMP(TypeClass.StaticImage);

    TypeClass typeClass;


    Filetyp(TypeClass typeClass) {
        this.typeClass = typeClass;
    }

    public enum TypeClass {
        Audio,
        StaticImage,
        Video,
        Unknown
    }
}
