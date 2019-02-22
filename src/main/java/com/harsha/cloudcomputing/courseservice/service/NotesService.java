package com.harsha.cloudcomputing.courseservice.service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

import com.harsha.cloudcomputing.courseservice.datamodel.Note;

import org.apache.commons.lang3.StringUtils;

/**
 * NotesService
 */
public class NotesService {
    private static HashMap<Long, Note> note_Map = new HashMap<>();

    public NotesService() {

    }

    /**
     * @param note to add
     * @return added note
     */
    public Note addNote(Note note) {
        long nextAvailableId = note_Map.size() + 1;
        String noteId = "N-" + StringUtils.leftPad(String.valueOf(nextAvailableId), 4, "0");
        note.setNoteId(noteId);
        note.setId(nextAvailableId);
        note_Map.put(nextAvailableId, note);
        return note;
    }

    /**
     * 
     * @param id of the note
     * @return note
     */
    public Note getNote(Long id) {
        return note_Map.get(id);
    }

    /**
     * @param id   of the note
     * @param note details to update
     * @return updated note
     */
    public Note updateNoteDetails(Long id, Note note) {
        Note noteToUpdate = note_Map.get(id);
        if (noteToUpdate == null) {
            return null;
        }
        note.setId(noteToUpdate.getId());
        note.setNoteId(noteToUpdate.getNoteId());
        note_Map.put(id, note);
        return note;
    }

    /**
     * 
     * @param id to delete
     * @return deleted note
     */
    public Note deleteNote(Long id) {
        Note noteToDelete = note_Map.get(id);
        note_Map.put(id, null);
        return noteToDelete;
    }

    /**
     * 
     * @param id of lecture
     * @return list of matching notes
     */
    public List<Note> getNotesOfLecture(Long id) {
        return note_Map.values().stream().filter(n -> n != null).filter(n -> n.getLecture().getId() == id)
                .collect(Collectors.toList());
    }

}