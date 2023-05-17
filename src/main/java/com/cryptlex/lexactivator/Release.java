package com.cryptlex.lexactivator;

public class Release {
    public int totalFiles;
    public boolean isPrivate;
    public boolean published;
    public String id;
    public String createdAt;
    public String updatedAt;
    public String name;
    public String channel;
    public String version;
    public String notes;
    public String publishedAt;
    public String productId;
    public String[] platforms;
    public ReleaseFile[] files;

    public static class ReleaseFile {
        public int size;
        public int downloads;
        public boolean secured;
        public String id;
        public String name;
        public String url;
        public String extension;
        public String checksum;
        public String releaseId;
        public String createdAt;
        public String updatedAt;
    }
}
