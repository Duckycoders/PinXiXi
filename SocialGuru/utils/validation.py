# utils/validation.py
def validate_user_id(user_id):
    return isinstance(user_id, int) and user_id > 0