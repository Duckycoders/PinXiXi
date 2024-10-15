# app.py
from flask import Flask, jsonify
from routes.reviews import reviews_bp
from dotenv import load_dotenv
import os

# 加载环境变量
load_dotenv()

app = Flask(__name__)

# 注册蓝图
app.register_blueprint(reviews_bp)

# 全局错误处理程序
#@app.errorhandler(Exception)
#def handle_exception(e):
#    print(f'Unhandled Exception: {e}')
#    return jsonify({'error': 'Internal server error.'}), 500

if __name__ == '__main__':
    app.run(debug=True, port=5000)