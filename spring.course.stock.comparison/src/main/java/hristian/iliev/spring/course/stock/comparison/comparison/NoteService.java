package hristian.iliev.spring.course.stock.comparison.comparison;

import hristian.iliev.spring.course.stock.comparison.comparison.entity.Note;

public interface NoteService {

  void deleteNote(int noteId);

  void createNote(Note note);

}
