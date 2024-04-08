// Generated by view binder compiler. Do not edit!
package kr.co.lion.mungnolja_nayeon1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.card.MaterialCardView;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import kr.co.lion.mungnolja_nayeon1.R;

public final class RowReservationListLastBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final MaterialCardView cardViewRowReservationListLastDetail;

  @NonNull
  public final CircleImageView imageViewRowReservationListLastPet;

  @NonNull
  public final CircleImageView imageViewRowReservationListLastPetsitter;

  @NonNull
  public final TextView textViewRowReservationListLastDate;

  @NonNull
  public final TextView textViewRowReservationListLastPetName;

  @NonNull
  public final TextView textViewRowReservationListLastPetsitterName;

  @NonNull
  public final TextView textViewRowReservationListLastTime;

  @NonNull
  public final TextView textViewRowReservationListPetCareType;

  @NonNull
  public final TextView textViewRowReservationiListLastWriteReview;

  private RowReservationListLastBinding(@NonNull LinearLayout rootView,
      @NonNull MaterialCardView cardViewRowReservationListLastDetail,
      @NonNull CircleImageView imageViewRowReservationListLastPet,
      @NonNull CircleImageView imageViewRowReservationListLastPetsitter,
      @NonNull TextView textViewRowReservationListLastDate,
      @NonNull TextView textViewRowReservationListLastPetName,
      @NonNull TextView textViewRowReservationListLastPetsitterName,
      @NonNull TextView textViewRowReservationListLastTime,
      @NonNull TextView textViewRowReservationListPetCareType,
      @NonNull TextView textViewRowReservationiListLastWriteReview) {
    this.rootView = rootView;
    this.cardViewRowReservationListLastDetail = cardViewRowReservationListLastDetail;
    this.imageViewRowReservationListLastPet = imageViewRowReservationListLastPet;
    this.imageViewRowReservationListLastPetsitter = imageViewRowReservationListLastPetsitter;
    this.textViewRowReservationListLastDate = textViewRowReservationListLastDate;
    this.textViewRowReservationListLastPetName = textViewRowReservationListLastPetName;
    this.textViewRowReservationListLastPetsitterName = textViewRowReservationListLastPetsitterName;
    this.textViewRowReservationListLastTime = textViewRowReservationListLastTime;
    this.textViewRowReservationListPetCareType = textViewRowReservationListPetCareType;
    this.textViewRowReservationiListLastWriteReview = textViewRowReservationiListLastWriteReview;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static RowReservationListLastBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static RowReservationListLastBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.row_reservation_list_last, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static RowReservationListLastBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.cardViewRowReservationListLastDetail;
      MaterialCardView cardViewRowReservationListLastDetail = ViewBindings.findChildViewById(rootView, id);
      if (cardViewRowReservationListLastDetail == null) {
        break missingId;
      }

      id = R.id.imageViewRowReservationListLastPet;
      CircleImageView imageViewRowReservationListLastPet = ViewBindings.findChildViewById(rootView, id);
      if (imageViewRowReservationListLastPet == null) {
        break missingId;
      }

      id = R.id.imageViewRowReservationListLastPetsitter;
      CircleImageView imageViewRowReservationListLastPetsitter = ViewBindings.findChildViewById(rootView, id);
      if (imageViewRowReservationListLastPetsitter == null) {
        break missingId;
      }

      id = R.id.textViewRowReservationListLastDate;
      TextView textViewRowReservationListLastDate = ViewBindings.findChildViewById(rootView, id);
      if (textViewRowReservationListLastDate == null) {
        break missingId;
      }

      id = R.id.textViewRowReservationListLastPetName;
      TextView textViewRowReservationListLastPetName = ViewBindings.findChildViewById(rootView, id);
      if (textViewRowReservationListLastPetName == null) {
        break missingId;
      }

      id = R.id.textViewRowReservationListLastPetsitterName;
      TextView textViewRowReservationListLastPetsitterName = ViewBindings.findChildViewById(rootView, id);
      if (textViewRowReservationListLastPetsitterName == null) {
        break missingId;
      }

      id = R.id.textViewRowReservationListLastTime;
      TextView textViewRowReservationListLastTime = ViewBindings.findChildViewById(rootView, id);
      if (textViewRowReservationListLastTime == null) {
        break missingId;
      }

      id = R.id.textViewRowReservationListPetCareType;
      TextView textViewRowReservationListPetCareType = ViewBindings.findChildViewById(rootView, id);
      if (textViewRowReservationListPetCareType == null) {
        break missingId;
      }

      id = R.id.textViewRowReservationiListLastWriteReview;
      TextView textViewRowReservationiListLastWriteReview = ViewBindings.findChildViewById(rootView, id);
      if (textViewRowReservationiListLastWriteReview == null) {
        break missingId;
      }

      return new RowReservationListLastBinding((LinearLayout) rootView,
          cardViewRowReservationListLastDetail, imageViewRowReservationListLastPet,
          imageViewRowReservationListLastPetsitter, textViewRowReservationListLastDate,
          textViewRowReservationListLastPetName, textViewRowReservationListLastPetsitterName,
          textViewRowReservationListLastTime, textViewRowReservationListPetCareType,
          textViewRowReservationiListLastWriteReview);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}
