package com.revature.data;

import com.revature.beans.Comment;

public interface CommentDAO extends GenericDAO<Comment> {
    public Comment add(Comment c);
}
