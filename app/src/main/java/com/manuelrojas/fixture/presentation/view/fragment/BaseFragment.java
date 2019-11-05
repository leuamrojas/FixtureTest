package com.manuelrojas.fixture.presentation.view.fragment;

import androidx.fragment.app.Fragment;

import android.widget.Toast;

import com.manuelrojas.fixture.presentation.di.HasComponent;

class BaseFragment extends Fragment {

    protected void showToastMessage(String message) {
        Toast.makeText(getActivity(), message, Toast.LENGTH_SHORT).show();
    }

    /**
     * Gets a component for dependency injection by its type.
     */
    @SuppressWarnings("unchecked")
    protected <C> C getComponent(Class<C> componentType) {
        return componentType.cast(((HasComponent<C>) getActivity()).getComponent());
    }

}
