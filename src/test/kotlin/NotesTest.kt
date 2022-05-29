import org.junit.Test

import org.junit.Assert.*

class NotesTest {


    @Test
    fun add() {
        val testNotes = Notes()
        val newNote = testNotes.add("title", "text")
        assertNotNull(newNote.second)
        assertEquals(0, newNote.first)
    }

    @Test
    fun createCommentTrue() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(newId,"text")
        assertNotNull(newComment.second)
        assertEquals(0, newComment.first)

    }
    @Test
    fun createCommentFalse() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(newId+1,"text")
        assertNull(newComment.second)
    }

    @Test
    fun getByIdTrue() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        assertNotNull(testNotes.getById(newId).second)
    }
    @Test
    fun getByIdFalse() {
        val testNotes = Notes()
        assertNull(testNotes.getById(12).second)
    }

    @Test
    fun deleteTrue() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        assertTrue(testNotes.delete(newId))
    }
    @Test
    fun deleteFalse() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        assertFalse(testNotes.delete(newId+1))
    }

    @Test
    fun deleteCommentTrue() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(newId,"text")
        assertTrue(testNotes.deleteComment(newComment.first))
    }
    @Test
    fun deleteCommentFalse() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(newId,"text")
        assertFalse(testNotes.deleteComment(newComment.first+1))
    }

    @Test
    fun editTrue() {
        val testNotes = Notes()
        val noteId = testNotes.add("title","text").first
        val newText = "new Text"
        val newTitle = "new Title"
        val editedNote = testNotes.edit(noteId,newTitle,newText).second
        assertNotNull(editedNote)
        assertEquals(editedNote!!.title,newTitle)
        assertEquals(editedNote!!.text,newText)
    }
    @Test
    fun editFalse() {
        val testNotes = Notes()
        val noteId = testNotes.add("title","text").first
        val newText = "new Text"
        val newTitle = "new Title"
        val editedNote = testNotes.edit(noteId+1,newTitle,newText).second
        assertNull(editedNote)
    }

    @Test
    fun editCommentTrue() {
        val testNotes = Notes()
        val noteId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(noteId,"text")
        val newCommentText = "new Text"
        val editedComment = testNotes.editComment(newComment.first,newCommentText)
        assertNotNull(editedComment.second)
        assertEquals(editedComment.second!!.text, newCommentText)
    }

    @Test
    fun editCommentFalse() {
        val testNotes = Notes()
        val noteId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(noteId,"text")
        val newCommentText = "new Text"
        val editedComment = testNotes.editComment(newComment.first+1,newCommentText)
        assertNull(editedComment.second)
    }

    @Test
    fun get() {
        val testNotes = Notes()
        testNotes.add("title1","text1")
        testNotes.add("title1","text1")
        testNotes.add("title1","text1")
        assertEquals(3,testNotes.get().size)
    }

    @Test
    fun getCommentsTrue() {
        val testNotes = Notes()
        val noteId = testNotes.add("title","text").first
        testNotes.createComment(noteId,"text")
        testNotes.createComment(noteId,"text")
        assertEquals(2,testNotes.getComments(noteId).size)
    }
    @Test
    fun getCommentsFalse() {
        val testNotes = Notes()
        val noteId = testNotes.add("title","text").first
        testNotes.createComment(noteId,"text")
        testNotes.createComment(noteId,"text")
        assertEquals(0,testNotes.getComments(noteId+1).size)
    }

    @Test
    fun restoreCommentTrue() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(newId,"text")
        testNotes.deleteComment(newComment.first)
        val restoredComment = testNotes.restoreComment(newComment.first).second
        assertNotNull(restoredComment)
    }
    @Test
    fun restoreCommentFalse() {
        val testNotes = Notes()
        val newId = testNotes.add("title","text").first
        val newComment = testNotes.createComment(newId,"text")
        val restoredComment = testNotes.restoreComment(newComment.first).second
        assertNull(restoredComment)
    }
}