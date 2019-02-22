package com.harsha.cloudcomputing.courseservice.datamodel;

/**
 * Note
 */
public class Note {
    private Long id;
    private String noteId;
    private String title;
    private String description;
    private String documentLink;
    private Lecture lecture;
    private String timeStamp;

    public Note() {

    }

    /**
     * 
     * @param noteId       the noteId to set
     * @param title        the title to set
     * @param description  the description to set
     * @param documentLink the documentLink to set
     * @param lecture      the lecture to set
     * @param timeStamp    the timeStamp to set
     */
    public Note(String noteId, String title, String description, String documentLink, Lecture lecture,
            String timeStamp) {
        this.setNoteId(noteId);
        this.setTitle(title);
        this.setDescription(description);
        this.setDocumentLink(documentLink);
        this.setLecture(lecture);
        this.setTimeStamp(timeStamp);
    }

    /**
     * @return the documentLink
     */
    public String getDocumentLink() {
        return documentLink;
    }

    /**
     * @param documentLink the documentLink to set
     */
    public void setDocumentLink(String documentLink) {
        this.documentLink = documentLink;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the id
     */
    public Long getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * @return the noteId
     */
    public String getNoteId() {
        return noteId;
    }

    /**
     * @param noteId the noteId to set
     */
    public void setNoteId(String noteId) {
        this.noteId = noteId;
    }

    /**
     * @return the timeStamp
     */
    public String getTimeStamp() {
        return timeStamp;
    }

    /**
     * @param timeStamp the timeStamp to set
     */
    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    /**
     * @return the lecture
     */
    public Lecture getLecture() {
        return lecture;
    }

    /**
     * @param lecture the lecture to set
     */
    public void setLecture(Lecture lecture) {
        this.lecture = lecture;
    }

    /**
     * @return the note object title
     */
    @Override
    public String toString() {
        return "Note: " + this.getTitle() + ", lecture: " + getLecture().getLectureId();
    }

}