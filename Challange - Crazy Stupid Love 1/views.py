from app import app 
from flask_login import UserMixin 
from flask_migrate import Migrate 
from flask_sqlalchemy import SQLAlchemy 
import hashlib 
db = SQLAlchemy(app) 
migrate = Migrate(app, db)
 
def hash_password(p): return hashlib.sha1(p).hexdigest() 

def is_admin(user): return user.id == 1 

class User(db.Model, UserMixin): 
__tablename__ = "users" 
id = db.Column(db.Integer, primary_key=True) 
username = db.Column(db.String(64), unique=True) 
password = db.Column(db.String(64)) 
name = db.Column(db.String(64)) 

def __init__(self, username, password, name=""): 
self.username = username 
self.password = password 
self.name = name 

def get_id(self): 
return unicode(self.id) 
# TODO: delete this. We don't want it to leak! 
INIT_DB = False 
if INIT_DB: 
	db.create_all() 

users = [ User("bestadminever", "53bbcb9803e5ea13fe175a516cbac4f9b60310f9", "Best Admin"), 
User("johnson_john", "3dd1254f02bce2215f7d1c827b3beb2d875e3519", "John Johnson"), 
User("stephen_stephenson", "f2f7d2a4e7c1d1fcc4ef7e7968586c99aade8b5b", "Stephen Stephenson"), ] 
for u in users: 
	db.session.add(u) 
	db.session.commit()