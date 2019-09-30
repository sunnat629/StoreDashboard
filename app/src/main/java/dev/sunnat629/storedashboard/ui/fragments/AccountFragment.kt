package dev.sunnat629.storedashboard.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import com.squareup.picasso.Picasso
import dev.sunnat629.storedashboard.R
import dev.sunnat629.storedashboard.models.entities.Contacts
import dev.sunnat629.storedashboard.models.entities.Users
import kotlinx.android.synthetic.main.account_fragment.*
import kotlinx.android.synthetic.main.contract_person_items.*
import kotlinx.android.synthetic.main.vendor_in_charge_label_items.*
import timber.log.Timber

class AccountFragment : BaseFragment() {

    private lateinit var userDetailsObserver: Observer<Users>
    private lateinit var errorMessageObserver: Observer<String>

    companion object {
        fun newInstance() = AccountFragment()
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.account_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setObserver()
    }

    private fun setObserver() {
        userDetailsObserver = Observer {
            profile_name.text = it.name
            Picasso.get()
                .load(it.avatarUrl)
                .placeholder(R.drawable.user)
                .into(profile_image)

            setContactPerson(it.contactPerson)
            setVendorInCharge(it.vendorInCharge)
        }

        errorMessageObserver = Observer {
            Timber.tag("ASDF").d(it.toString())
        }

        viewModel.userDetails.observe(this, userDetailsObserver)
        viewModel.errorMessage.observe(this, errorMessageObserver)
    }

    private fun setVendorInCharge(vendorInCharge: Contacts) {
        vendor_p_name.text = vendorInCharge.primary.name
        vendorInCharge.primary.mobileNumber?.let {
            vendor_p_mobile.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            vendor_p_mobile.text = it
        }

        vendorInCharge.primary.email?.let {
            vendor_p_email.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            vendor_p_email.text = it
        }

        vendor_name.text = vendorInCharge.secondary.name
        vendorInCharge.secondary.mobileNumber?.let {
            vendor_mobile.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            vendor_mobile.text = it
        }

        vendorInCharge.secondary.email?.let {
            vendor_email.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            vendor_email.text = it
        }
    }

    private fun setContactPerson(contactPerson: Contacts) {
        contact_p_name.text = contactPerson.primary.name
        contactPerson.primary.mobileNumber?.let {
            contact_p_mobile.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            contact_p_mobile.text = it
        }

        contactPerson.primary.email?.let {
            contact_p_email.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            contact_p_email.text = it
        }

        contact_name.text = contactPerson.secondary.name
        contactPerson.secondary.mobileNumber?.let {
            contact_mobile.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            contact_mobile.text = it
        }

        contactPerson.secondary.email?.let {
            contact_email.visibility = if (it.isNotEmpty()) View.VISIBLE else View.GONE
            contact_email.text = it
        }
    }

    override fun onDetach() {
        super.onDetach()
        viewModel.userDetails.removeObservers(this) // remove all observers of userDetails after destroy this fragment
        viewModel.errorMessage.removeObservers(this) // remove all observers of errorMessage after destroy this fragment
    }
}