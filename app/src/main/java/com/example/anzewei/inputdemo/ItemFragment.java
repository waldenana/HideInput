package com.example.anzewei.inputdemo;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.anzewei.inputdemo.dummy.DummyContent;
import com.example.anzewei.inputdemo.dummy.DummyContent.DummyItem;
import com.github.anzewei.hideinput.InputHelper;

/**
 * A fragment representing a list of Items.
 * <p/>
 * Activities containing this fragment MUST implement the {@link OnListFragmentInteractionListener}
 * interface.
 */
public class ItemFragment extends Fragment {

    private int mColumnCount = 1;
    private OnListFragmentInteractionListener mListener = new OnListFragmentInteractionListener() {
        @Override
        public void onListFragmentInteraction(DummyItem item) {
            PlusOneFragment fragment = new PlusOneFragment();
            FragmentTransaction transaction = getFragmentManager().beginTransaction();
            transaction.setCustomAnimations(R.anim.slide_right_in,R.anim.slide_out_left);
            transaction.replace(R.id.frame,fragment, item.id);
            transaction.addToBackStack(item.id);
            transaction.commit();
        }
    };

    private RecyclerView mRecyclerView;
    private SearchView mSearchView;
    private MyItemRecyclerViewAdapter mRecyclerViewAdapter = new MyItemRecyclerViewAdapter(DummyContent.ITEMS, mListener);

    /**
     * Mandatory empty constructor for the fragment manager to instantiate the
     * fragment (e.g. upon screen orientation changes).
     */
    public ItemFragment() {
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_item_list, container, false);
        mRecyclerView = (RecyclerView) view.findViewById(R.id.list);
        mSearchView = (SearchView) view.findViewById(R.id.search_view);
        mSearchView.setIconifiedByDefault(false);
        mSearchView.onActionViewExpanded();
        mSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                mRecyclerViewAdapter.getFilter().filter(newText);
                return true;
            }
        });
        mRecyclerView.setAdapter(mRecyclerViewAdapter);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((BaseActivity)getActivity()).getInputHelper().setMode(InputHelper.MODE_BOTTOM);
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p/>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnListFragmentInteractionListener {
        // TODO: Update argument type and name
        void onListFragmentInteraction(DummyItem item);
    }
}
