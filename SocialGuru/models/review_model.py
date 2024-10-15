# models/review_model.py

from dataclasses import dataclass
from datetime import datetime
from db import get_db_connection

@dataclass
class ReviewModel:
    review_id: int
    user_id: int
    product_id: int
    seller_id: int
    rating: int
    review_text: str
    created_time: datetime
    updated_time: datetime

    def to_dict(self):
        return {
            'review_id': self.review_id,
            'user_id': self.user_id,
            'product_id': self.product_id,
            'seller_id': self.seller_id,
            'rating': self.rating,
            'review_text': self.review_text,
            'created_time': self.created_time.isoformat(),
            'updated_time': self.updated_time.isoformat()
        }

    @staticmethod
    def get_recent_reviews_by_user_id(user_id):
        query = """
            SELECT review_id, user_id, product_id, seller_id, rating, review_text, created_time, updated_time
            FROM Reviews
            WHERE user_id = %s
            ORDER BY created_time DESC
            LIMIT 5;
        """
        connection = get_db_connection()
        try:
            with connection.cursor() as cursor:
                cursor.execute(query, (user_id,))
                rows = cursor.fetchall()
                reviews = []
                for row in rows:
                    review = ReviewModel(
                        review_id=row[0],
                        user_id=row[1],
                        product_id=row[2],
                        seller_id=row[3],
                        rating=row[4],
                        review_text=row[5],
                        created_time=row[6],
                        updated_time=row[7]
                    )
                    reviews.append(review)
                return reviews
        except Exception as e:
            print(f'Error in get_recent_reviews_by_user_id: {e}')
            raise e
        finally:
            connection.close()