package sample.com.emiratesauctionassignment.presentation.presenter.interfaces;

import java.util.ArrayList;

import sample.com.emiratesauctionassignment.domain.models.TaskObj;


/**
 * Created by elmalah on 3/2/2017.
 */

public interface TasksListCommunicator {
    void onListItemSelected(TaskObj taskObj, int position);
    ArrayList<TaskObj> getAllOrdersListItems();
    void setAllOrdersListItems(ArrayList<TaskObj> ordersListItems);
}
