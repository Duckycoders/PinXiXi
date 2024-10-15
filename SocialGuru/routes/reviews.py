# routes/reviews.py
from flask import Blueprint, render_template, jsonify
from controllers.reviews_controller import get_recent_reviews_by_user

reviews_bp = Blueprint('reviews_bp', __name__)

# 渲染前端页面的路由
@reviews_bp.route('/', methods=['GET'])
def index():
    return render_template('recent_reviews.html')

# 获取最近评论的 API 端点
@reviews_bp.route('/users/<int:user_id>/reviews/recent', methods=['GET'])
def recent_reviews(user_id):
    return get_recent_reviews_by_user(user_id)