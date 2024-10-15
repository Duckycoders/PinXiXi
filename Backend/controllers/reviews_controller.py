# controllers/reviews_controller.py
from flask import jsonify
import traceback
from models.review_model import ReviewModel
from utils.validation import validate_user_id

def get_recent_reviews_by_user(user_id):
    # 输入验证
    if not validate_user_id(user_id):
        return jsonify({'error': 'Invalid user ID.'}), 400

    try:
        reviews = ReviewModel.get_recent_reviews_by_user_id(user_id)
        reviews_dict = [review.to_dict() for review in reviews]
        return jsonify(reviews_dict), 200
    except Exception as e:
        print(f'Error in get_recent_reviews_by_user: {e}')
        traceback.print_exc()
        return jsonify({'error': 'Internal server error.'}), 500