// static/js/scripts.js
document.addEventListener('DOMContentLoaded', function () {
    const form = document.getElementById('reviewForm');
    form.addEventListener('submit', function (event) {
        event.preventDefault();
        getRecentReviews();
    });
});

async function getRecentReviews() {
    const userIdInput = document.getElementById('userIdInput');
    const userId = userIdInput.value.trim();
    const messageDiv = document.getElementById('message');
    const reviewsContainer = document.getElementById('reviewsContainer');

    // 清除之前的消息和评论
    messageDiv.textContent = '';
    reviewsContainer.innerHTML = '';

    // 输入验证
    if (!userId || isNaN(userId) || Number(userId) <= 0) {
        messageDiv.innerHTML = '<p class="error">请输入有效的用户 ID。</p>';
        return;
    }

    try {
        messageDiv.textContent = '加载中...';

        const response = await fetch(`/users/${userId}/reviews/recent`);
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || '无法检索评论。');
        }
        const reviews = await response.json();
        displayReviews(reviews);
        messageDiv.textContent = '';
    } catch (error) {
        console.error('Error fetching reviews:', error);
        messageDiv.innerHTML = `<p class="error">${error.message}</p>`;
    }
}

function displayReviews(reviews) {
    const reviewsContainer = document.getElementById('reviewsContainer');

    if (reviews.length === 0) {
        reviewsContainer.innerHTML = '<p>未找到该用户的评论。</p>';
        return;
    }

    const list = document.createElement('ul');

    reviews.forEach((review) => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <p><strong>产品 ID：</strong> ${review.product_id}</p>
            <p><strong>卖家 ID：</strong> ${review.seller_id}</p>
            <p><strong>评分：</strong> ${review.rating}</p>
            <p><strong>评论内容：</strong> ${review.review_text}</p>
            <p><strong>创建时间：</strong> ${formatDate(review.created_time)}</p>
            <p><strong>更新时间：</strong> ${formatDate(review.updated_time)}</p>
        `;
        list.appendChild(listItem);
    });

    reviewsContainer.appendChild(list);
}

function formatDate(dateString) {
    const date = new Date(dateString);
    return date.toLocaleString();
}