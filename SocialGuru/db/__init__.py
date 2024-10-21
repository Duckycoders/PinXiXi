# db/__init__.py
import psycopg2
import os

def get_db_connection():
    connection = psycopg2.connect(
        dbname=os.getenv('DB_NAME', 'mydatabase'),
        user=os.getenv('DB_USER', 'myuser'),
        password=os.getenv('DB_PASSWORD', 'yoshinolism'),
        host=os.getenv('DB_HOST', 'localhost'),
        port=os.getenv('DB_PORT', 5432)
    )
    connection.set_client_encoding('UTF8')
    return connection