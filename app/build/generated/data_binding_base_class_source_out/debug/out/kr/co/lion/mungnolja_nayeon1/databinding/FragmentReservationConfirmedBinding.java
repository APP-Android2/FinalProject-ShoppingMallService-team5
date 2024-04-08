// Generated by view binder compiler. Do not edit!
package kr.co.lion.mungnolja_nayeon1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.AppCompatButton;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import kr.co.lion.mungnolja_nayeon1.R;

public final class FragmentReservationConfirmedBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final AppCompatButton buttonReservationConfirmedDone;

  @NonNull
  public final MaterialCardView cardReservationConfirmedDetail;

  @NonNull
  public final View divider5;

  @NonNull
  public final View divider6;

  @NonNull
  public final CircleImageView imageViewReservationConfirmedPet;

  @NonNull
  public final CircleImageView imageViewReservationConfirmedPetSitter;

  @NonNull
  public final RatingBar ratingBarReservationConfirmedPetSitter;

  @NonNull
  public final TextView textView4;

  @NonNull
  public final TextView textViewReservationConfirmedAddress;

  @NonNull
  public final TextView textViewReservationConfirmedDateTime;

  @NonNull
  public final TextView textViewReservationConfirmedPetCareType;

  @NonNull
  public final TextView textViewReservationConfirmedPetName;

  @NonNull
  public final TextView textViewReservationConfirmedPetSitterCareer;

  @NonNull
  public final TextView textViewReservationConfirmedPetSitterName;

  @NonNull
  public final MaterialToolbar toolbarReservationConfirmed;

  private FragmentReservationConfirmedBinding(@NonNull LinearLayout rootView,
      @NonNull AppCompatButton buttonReservationConfirmedDone,
      @NonNull MaterialCardView cardReservationConfirmedDetail, @NonNull View divider5,
      @NonNull View divider6, @NonNull CircleImageView imageViewReservationConfirmedPet,
      @NonNull CircleImageView imageViewReservationConfirmedPetSitter,
      @NonNull RatingBar ratingBarReservationConfirmedPetSitter, @NonNull TextView textView4,
      @NonNull TextView textViewReservationConfirmedAddress,
      @NonNull TextView textViewReservationConfirmedDateTime,
      @NonNull TextView textViewReservationConfirmedPetCareType,
      @NonNull TextView textViewReservationConfirmedPetName,
      @NonNull TextView textViewReservationConfirmedPetSitterCareer,
      @NonNull TextView textViewReservationConfirmedPetSitterName,
      @NonNull MaterialToolbar toolbarReservationConfirmed) {
    this.rootView = rootView;
    this.buttonReservationConfirmedDone = buttonReservationConfirmedDone;
    this.cardReservationConfirmedDetail = cardReservationConfirmedDetail;
    this.divider5 = divider5;
    this.divider6 = divider6;
    this.imageViewReservationConfirmedPet = imageViewReservationConfirmedPet;
    this.imageViewReservationConfirmedPetSitter = imageViewReservationConfirmedPetSitter;
    this.ratingBarReservationConfirmedPetSitter = ratingBarReservationConfirmedPetSitter;
    this.textView4 = textView4;
    this.textViewReservationConfirmedAddress = textViewReservationConfirmedAddress;
    this.textViewReservationConfirmedDateTime = textViewReservationConfirmedDateTime;
    this.textViewReservationConfirmedPetCareType = textViewReservationConfirmedPetCareType;
    this.textViewReservationConfirmedPetName = textViewReservationConfirmedPetName;
    this.textViewReservationConfirmedPetSitterCareer = textViewReservationConfirmedPetSitterCareer;
    this.textViewReservationConfirmedPetSitterName = textViewReservationConfirmedPetSitterName;
    this.toolbarReservationConfirmed = toolbarReservationConfirmed;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentReservationConfirmedBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentReservationConfirmedBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_reservation_confirmed, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentReservationConfirmedBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonReservationConfirmedDone;
      AppCompatButton buttonReservationConfirmedDone = ViewBindings.findChildViewById(rootView, id);
      if (buttonReservationConfirmedDone == null) {
        break missingId;
      }

      id = R.id.cardReservationConfirmedDetail;
      MaterialCardView cardReservationConfirmedDetail = ViewBindings.findChildViewById(rootView, id);
      if (cardReservationConfirmedDetail == null) {
        break missingId;
      }

      id = R.id.divider5;
      View divider5 = ViewBindings.findChildViewById(rootView, id);
      if (divider5 == null) {
        break missingId;
      }

      id = R.id.divider6;
      View divider6 = ViewBindings.findChildViewById(rootView, id);
      if (divider6 == null) {
        break missingId;
      }

      id = R.id.imageViewReservationConfirmedPet;
      CircleImageView imageViewReservationConfirmedPet = ViewBindings.findChildViewById(rootView, id);
      if (imageViewReservationConfirmedPet == null) {
        break missingId;
      }

      id = R.id.imageViewReservationConfirmedPetSitter;
      CircleImageView imageViewReservationConfirmedPetSitter = ViewBindings.findChildViewById(rootView, id);
      if (imageViewReservationConfirmedPetSitter == null) {
        break missingId;
      }

      id = R.id.ratingBarReservationConfirmedPetSitter;
      RatingBar ratingBarReservationConfirmedPetSitter = ViewBindings.findChildViewById(rootView, id);
      if (ratingBarReservationConfirmedPetSitter == null) {
        break missingId;
      }

      id = R.id.textView4;
      TextView textView4 = ViewBindings.findChildViewById(rootView, id);
      if (textView4 == null) {
        break missingId;
      }

      id = R.id.textViewReservationConfirmedAddress;
      TextView textViewReservationConfirmedAddress = ViewBindings.findChildViewById(rootView, id);
      if (textViewReservationConfirmedAddress == null) {
        break missingId;
      }

      id = R.id.textViewReservationConfirmedDateTime;
      TextView textViewReservationConfirmedDateTime = ViewBindings.findChildViewById(rootView, id);
      if (textViewReservationConfirmedDateTime == null) {
        break missingId;
      }

      id = R.id.textViewReservationConfirmedPetCareType;
      TextView textViewReservationConfirmedPetCareType = ViewBindings.findChildViewById(rootView, id);
      if (textViewReservationConfirmedPetCareType == null) {
        break missingId;
      }

      id = R.id.textViewReservationConfirmedPetName;
      TextView textViewReservationConfirmedPetName = ViewBindings.findChildViewById(rootView, id);
      if (textViewReservationConfirmedPetName == null) {
        break missingId;
      }

      id = R.id.textViewReservationConfirmedPetSitterCareer;
      TextView textViewReservationConfirmedPetSitterCareer = ViewBindings.findChildViewById(rootView, id);
      if (textViewReservationConfirmedPetSitterCareer == null) {
        break missingId;
      }

      id = R.id.textViewReservationConfirmedPetSitterName;
      TextView textViewReservationConfirmedPetSitterName = ViewBindings.findChildViewById(rootView, id);
      if (textViewReservationConfirmedPetSitterName == null) {
        break missingId;
      }

      id = R.id.toolbarReservationConfirmed;
      MaterialToolbar toolbarReservationConfirmed = ViewBindings.findChildViewById(rootView, id);
      if (toolbarReservationConfirmed == null) {
        break missingId;
      }

      return new FragmentReservationConfirmedBinding((LinearLayout) rootView,
          buttonReservationConfirmedDone, cardReservationConfirmedDetail, divider5, divider6,
          imageViewReservationConfirmedPet, imageViewReservationConfirmedPetSitter,
          ratingBarReservationConfirmedPetSitter, textView4, textViewReservationConfirmedAddress,
          textViewReservationConfirmedDateTime, textViewReservationConfirmedPetCareType,
          textViewReservationConfirmedPetName, textViewReservationConfirmedPetSitterCareer,
          textViewReservationConfirmedPetSitterName, toolbarReservationConfirmed);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
