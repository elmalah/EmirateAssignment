package sample.com.emiratesauctionassignment.presentation.presenter.interfaces;

import java.util.ArrayList;

import sample.com.emiratesauctionassignment.domain.models.TaskObj;


/**
 * Created by elmalah on 3/2/2017.
 */

public interface CommetsListCommunicator {
    void onListItemSelected(TaskObj.CommentObj commentObj, int position);
    ArrayList<TaskObj.CommentObj> getAllCommentsListItems();
    void setAllCommentsListItems(ArrayList<TaskObj.CommentObj> ordersListItems);
}
