"""create store table

Revision ID: e7e54aef72aa
Revises: 
Create Date: 2020-05-04 20:37:50.217975

"""
from alembic import op
import sqlalchemy as sa


# revision identifiers, used by Alembic.
revision = 'e7e54aef72aa'
down_revision = None
branch_labels = None
depends_on = None


def upgrade():
    op.create_table(
        'store',
        sa.Column('id', sa.Integer, primary_key=True),
        sa.Column('title', sa.String(500), nullable=False),
        sa.Column('category', sa.String(500)),
        sa.Column('sigoon', sa.String(500)),
        sa.Column('address', sa.String(500)),
        sa.Column('phone_number', sa.String(500)),
        sa.Column('post_code', sa.String(500)),
        sa.Column('latitude', sa.String(500)),
        sa.Column('longitude', sa.String(500)),
        sa.Column('update_date', sa.String(500)),
    )


def downgrade():
    op.drop_table('store')
