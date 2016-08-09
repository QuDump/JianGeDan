package com.qudump.jiangedan.net.bean;

import java.util.List;

/**
 * Created by dili on 2016/8/9.
 */
public class LittleVideoBean {
    private long comment_post_ID;
    private long comment_ID;
    private String comment_author;
    private String comment_author_email;
    private String comment_date;
    private String comment_content;
    private String text_content;
    private int comment_approved;
    private int vote_negative;
    private int vote_positive;
    private List<VideoInfo> videos;

    public long getComment_post_ID() {
        return comment_post_ID;
    }

    public void setComment_post_ID(long comment_post_ID) {
        this.comment_post_ID = comment_post_ID;
    }

    public long getComment_ID() {
        return comment_ID;
    }

    public void setComment_ID(long comment_ID) {
        this.comment_ID = comment_ID;
    }

    public String getComment_author() {
        return comment_author;
    }

    public void setComment_author(String comment_author) {
        this.comment_author = comment_author;
    }

    public String getComment_author_email() {
        return comment_author_email;
    }

    public void setComment_author_email(String comment_author_email) {
        this.comment_author_email = comment_author_email;
    }

    public String getComment_date() {
        return comment_date;
    }

    public void setComment_date(String comment_date) {
        this.comment_date = comment_date;
    }

    public String getComment_content() {
        return comment_content;
    }

    public void setComment_content(String comment_content) {
        this.comment_content = comment_content;
    }

    public String getText_content() {
        return text_content;
    }

    public void setText_content(String text_content) {
        this.text_content = text_content;
    }

    public int getComment_approved() {
        return comment_approved;
    }

    public void setComment_approved(int comment_approved) {
        this.comment_approved = comment_approved;
    }

    public int getVote_negative() {
        return vote_negative;
    }

    public void setVote_negative(int vote_negative) {
        this.vote_negative = vote_negative;
    }

    public int getVote_positive() {
        return vote_positive;
    }

    public void setVote_positive(int vote_positive) {
        this.vote_positive = vote_positive;
    }

    public List<VideoInfo> getVideos() {
        return videos;
    }

    public void setVideos(List<VideoInfo> videos) {
        this.videos = videos;
    }

    public class VideoInfo {
        private String category;
        private int comment_count;
        private String copyright_type;
        private String description;
        private float duration;
        private String id;
        private String link;
        private String player;
        private String public_type;
        private String published;
        private String state;
        private List<String> streamtypes;
        private String tags;
        private String thumbnail;
        private String thumbnail_v2;
        private String title;
        private String video_source;
        private String view_count;
        private User user;

        public String getCategory() {
            return category;
        }

        public void setCategory(String category) {
            this.category = category;
        }

        public int getComment_count() {
            return comment_count;
        }

        public void setComment_count(int comment_count) {
            this.comment_count = comment_count;
        }

        public String getCopyright_type() {
            return copyright_type;
        }

        public void setCopyright_type(String copyright_type) {
            this.copyright_type = copyright_type;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public float getDuration() {
            return duration;
        }

        public void setDuration(float duration) {
            this.duration = duration;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getLink() {
            return link;
        }

        public void setLink(String link) {
            this.link = link;
        }

        public String getPlayer() {
            return player;
        }

        public void setPlayer(String player) {
            this.player = player;
        }

        public String getPublic_type() {
            return public_type;
        }

        public void setPublic_type(String public_type) {
            this.public_type = public_type;
        }

        public String getPublished() {
            return published;
        }

        public void setPublished(String published) {
            this.published = published;
        }

        public String getState() {
            return state;
        }

        public void setState(String state) {
            this.state = state;
        }

        public List<String> getStreamtypes() {
            return streamtypes;
        }

        public void setStreamtypes(List<String> streamtypes) {
            this.streamtypes = streamtypes;
        }

        public String getTags() {
            return tags;
        }

        public void setTags(String tags) {
            this.tags = tags;
        }

        public String getThumbnail() {
            return thumbnail;
        }

        public void setThumbnail(String thumbnail) {
            this.thumbnail = thumbnail;
        }

        public String getThumbnail_v2() {
            return thumbnail_v2;
        }

        public void setThumbnail_v2(String thumbnail_v2) {
            this.thumbnail_v2 = thumbnail_v2;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getVideo_source() {
            return video_source;
        }

        public void setVideo_source(String video_source) {
            this.video_source = video_source;
        }

        public String getView_count() {
            return view_count;
        }

        public void setView_count(String view_count) {
            this.view_count = view_count;
        }

        public User getUser() {
            return user;
        }

        public void setUser(User user) {
            this.user = user;
        }

        public class User {
            private long id;
            private String link;
            private String name;

            public long getId() {
                return id;
            }

            public void setId(long id) {
                this.id = id;
            }

            public String getLink() {
                return link;
            }

            public void setLink(String link) {
                this.link = link;
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }
        }
    }



}
