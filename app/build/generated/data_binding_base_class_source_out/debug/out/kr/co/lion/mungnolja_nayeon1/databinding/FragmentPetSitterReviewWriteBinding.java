// Generated by view binder compiler. Do not edit!
package kr.co.lion.mungnolja_nayeon1.databinding;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewbinding.ViewBinding;
import androidx.viewbinding.ViewBindings;
import com.google.android.material.appbar.MaterialToolbar;
import com.google.android.material.card.MaterialCardView;
import com.google.android.material.textfield.TextInputLayout;
import de.hdodenhof.circleimageview.CircleImageView;
import java.lang.NullPointerException;
import java.lang.Override;
import java.lang.String;
import kr.co.lion.mungnolja_nayeon1.R;

public final class FragmentPetSitterReviewWriteBinding implements ViewBinding {
  @NonNull
  private final LinearLayout rootView;

  @NonNull
  public final Button buttonPetsitterReviewWriteDone;

  @NonNull
  public final MaterialCardView cardPetSitterReviewWriteDetail;

  @NonNull
  public final View divider6;

  @NonNull
  public final CircleImageView imageViewPetSitterReviewWrite;

  @NonNull
  public final RatingBar ratingBar2;

  @NonNull
  public final RatingBar ratingBarPetSitterReviewWrite;

  @NonNull
  public final TextView textView5;

  @NonNull
  public final TextView textView6;

  @NonNull
  public final TextView textViewPetSitterReviewWriteCountReview;

  @NonNull
  public final TextView textViewPetSitterReviewWriteName;

  @NonNull
  public final TextView textViewPetSitterReviewWriteScore;

  @NonNull
  public final TextInputLayout textfieldPetsitterReviewWrite;

  @NonNull
  public final MaterialToolbar toolbarPetSitterReviewWrite;

  private FragmentPetSitterReviewWriteBinding(@NonNull LinearLayout rootView,
      @NonNull Button buttonPetsitterReviewWriteDone,
      @NonNull MaterialCardView cardPetSitterReviewWriteDetail, @NonNull View divider6,
      @NonNull CircleImageView imageViewPetSitterReviewWrite, @NonNull RatingBar ratingBar2,
      @NonNull RatingBar ratingBarPetSitterReviewWrite, @NonNull TextView textView5,
      @NonNull TextView textView6, @NonNull TextView textViewPetSitterReviewWriteCountReview,
      @NonNull TextView textViewPetSitterReviewWriteName,
      @NonNull TextView textViewPetSitterReviewWriteScore,
      @NonNull TextInputLayout textfieldPetsitterReviewWrite,
      @NonNull MaterialToolbar toolbarPetSitterReviewWrite) {
    this.rootView = rootView;
    this.buttonPetsitterReviewWriteDone = buttonPetsitterReviewWriteDone;
    this.cardPetSitterReviewWriteDetail = cardPetSitterReviewWriteDetail;
    this.divider6 = divider6;
    this.imageViewPetSitterReviewWrite = imageViewPetSitterReviewWrite;
    this.ratingBar2 = ratingBar2;
    this.ratingBarPetSitterReviewWrite = ratingBarPetSitterReviewWrite;
    this.textView5 = textView5;
    this.textView6 = textView6;
    this.textViewPetSitterReviewWriteCountReview = textViewPetSitterReviewWriteCountReview;
    this.textViewPetSitterReviewWriteName = textViewPetSitterReviewWriteName;
    this.textViewPetSitterReviewWriteScore = textViewPetSitterReviewWriteScore;
    this.textfieldPetsitterReviewWrite = textfieldPetsitterReviewWrite;
    this.toolbarPetSitterReviewWrite = toolbarPetSitterReviewWrite;
  }

  @Override
  @NonNull
  public LinearLayout getRoot() {
    return rootView;
  }

  @NonNull
  public static FragmentPetSitterReviewWriteBinding inflate(@NonNull LayoutInflater inflater) {
    return inflate(inflater, null, false);
  }

  @NonNull
  public static FragmentPetSitterReviewWriteBinding inflate(@NonNull LayoutInflater inflater,
      @Nullable ViewGroup parent, boolean attachToParent) {
    View root = inflater.inflate(R.layout.fragment_pet_sitter_review_write, parent, false);
    if (attachToParent) {
      parent.addView(root);
    }
    return bind(root);
  }

  @NonNull
  public static FragmentPetSitterReviewWriteBinding bind(@NonNull View rootView) {
    // The body of this method is generated in a way you would not otherwise write.
    // This is done to optimize the compiled bytecode for size and performance.
    int id;
    missingId: {
      id = R.id.buttonPetsitterReviewWriteDone;
      Button buttonPetsitterReviewWriteDone = ViewBindings.findChildViewById(rootView, id);
      if (buttonPetsitterReviewWriteDone == null) {
        break missingId;
      }

      id = R.id.cardPetSitterReviewWriteDetail;
      MaterialCardView cardPetSitterReviewWriteDetail = ViewBindings.findChildViewById(rootView, id);
      if (cardPetSitterReviewWriteDetail == null) {
        break missingId;
      }

      id = R.id.divider6;
      View divider6 = ViewBindings.findChildViewById(rootView, id);
      if (divider6 == null) {
        break missingId;
      }

      id = R.id.imageViewPetSitterReviewWrite;
      CircleImageView imageViewPetSitterReviewWrite = ViewBindings.findChildViewById(rootView, id);
      if (imageViewPetSitterReviewWrite == null) {
        break missingId;
      }

      id = R.id.ratingBar2;
      RatingBar ratingBar2 = ViewBindings.findChildViewById(rootView, id);
      if (ratingBar2 == null) {
        break missingId;
      }

      id = R.id.ratingBarPetSitterReviewWrite;
      RatingBar ratingBarPetSitterReviewWrite = ViewBindings.findChildViewById(rootView, id);
      if (ratingBarPetSitterReviewWrite == null) {
        break missingId;
      }

      id = R.id.textView5;
      TextView textView5 = ViewBindings.findChildViewById(rootView, id);
      if (textView5 == null) {
        break missingId;
      }

      id = R.id.textView6;
      TextView textView6 = ViewBindings.findChildViewById(rootView, id);
      if (textView6 == null) {
        break missingId;
      }

      id = R.id.textViewPetSitterReviewWriteCountReview;
      TextView textViewPetSitterReviewWriteCountReview = ViewBindings.findChildViewById(rootView, id);
      if (textViewPetSitterReviewWriteCountReview == null) {
        break missingId;
      }

      id = R.id.textViewPetSitterReviewWriteName;
      TextView textViewPetSitterReviewWriteName = ViewBindings.findChildViewById(rootView, id);
      if (textViewPetSitterReviewWriteName == null) {
        break missingId;
      }

      id = R.id.textViewPetSitterReviewWriteScore;
      TextView textViewPetSitterReviewWriteScore = ViewBindings.findChildViewById(rootView, id);
      if (textViewPetSitterReviewWriteScore == null) {
        break missingId;
      }

      id = R.id.textfieldPetsitterReviewWrite;
      TextInputLayout textfieldPetsitterReviewWrite = ViewBindings.findChildViewById(rootView, id);
      if (textfieldPetsitterReviewWrite == null) {
        break missingId;
      }

      id = R.id.toolbarPetSitterReviewWrite;
      MaterialToolbar toolbarPetSitterReviewWrite = ViewBindings.findChildViewById(rootView, id);
      if (toolbarPetSitterReviewWrite == null) {
        break missingId;
      }

      return new FragmentPetSitterReviewWriteBinding((LinearLayout) rootView,
          buttonPetsitterReviewWriteDone, cardPetSitterReviewWriteDetail, divider6,
          imageViewPetSitterReviewWrite, ratingBar2, ratingBarPetSitterReviewWrite, textView5,
          textView6, textViewPetSitterReviewWriteCountReview, textViewPetSitterReviewWriteName,
          textViewPetSitterReviewWriteScore, textfieldPetsitterReviewWrite,
          toolbarPetSitterReviewWrite);
    }
    String missingId = rootView.getResources().getResourceName(id);
    throw new NullPointerException("Missing required view with ID: ".concat(missingId));
  }
}