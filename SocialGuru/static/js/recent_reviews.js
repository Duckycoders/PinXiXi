// static/js/recent_reviews.js

async function getRecentReviews() {
    const userId = document.getElementById('userIdInput').value.trim();
    const reviewsContainer = document.getElementById('reviewsContainer');
    reviewsContainer.innerHTML = '';

    if (!userId || isNaN(userId) || Number(userId) <= 0) {
        reviewsContainer.innerHTML = '<p class="error">请输入有效的用户ID。</p>';
        return;
    }

    try {
        const response = await fetch(`/api/reviews/users/${userId}/recent`);
        if (!response.ok) {
            const errorData = await response.json();
            throw new Error(errorData.error || '无法获取反馈。');
        }
        const reviews = await response.json();
        displayReviews(reviews);
    } catch (error) {
        console.error('获取反馈时出错：', error);
        reviewsContainer.innerHTML = `<p class="error">${error.message}</p>`;
    }
}

function displayReviews(reviews) {
    const reviewsContainer = document.getElementById('reviewsContainer');

    if (reviews.length === 0) {
        reviewsContainer.innerHTML = '<p>未找到反馈。</p>';
        return;
    }

    const list = document.createElement('ul');

    reviews.forEach((review) => {
        const listItem = document.createElement('li');
        listItem.innerHTML = `
            <p><strong>产品ID：</strong> ${review.product_id}</p>
            <p><strong>卖家ID：</strong> ${review.seller_id}</p>
            <p><strong>评分：</strong> ${review.rating}</p>
            <p><strong>评论：</strong> ${review.review_text}</p>
            <p><strong>创建时间：</strong> ${new Date(review.created_time).toLocaleString()}</p>
            <hr />
        `;
        list.appendChild(listItem);
    });

    reviewsContainer.appendChild(list);
}