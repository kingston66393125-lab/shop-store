import React, { useState } from 'react'
import { createRoot } from 'react-dom/client'

const api = async (path, method = 'GET', body) => {
  const res = await fetch(`http://localhost:8080${path}`, {
    method,
    headers: { 'Content-Type': 'application/json' },
    body: body ? JSON.stringify(body) : undefined,
  })
  if (!res.ok) throw new Error(await res.text())
  return res.json()
}

function App() {
  const [username, setUsername] = useState('user1')
  const [password, setPassword] = useState('123456')
  const [userId, setUserId] = useState(null)
  const [books, setBooks] = useState([])
  const [order, setOrder] = useState(null)

  return (
    <div style={{ fontFamily: 'sans-serif', margin: 20 }}>
      <h1>二手书商城 Demo</h1>
      <button onClick={async () => {
        const u = await api('/api/auth/register', 'POST', { username, password })
        setUserId(u.userId)
      }}>注册</button>
      <button onClick={async () => {
        const u = await api('/api/auth/login', 'POST', { username, password })
        setUserId(u.userId)
      }}>登录</button>
      <button onClick={async () => setBooks(await api('/api/books'))}>加载图书</button>
      <pre>{JSON.stringify(books, null, 2)}</pre>
      {books[0] && userId && <button onClick={async () => {
        const o = await api('/api/orders', 'POST', { userId, bookId: books[0].id, quantity: 1 })
        setOrder(o)
      }}>下单</button>}
      {order && <button onClick={async () => await api('/api/payments', 'POST', { orderId: order.id })}>支付</button>}
      {order && <button onClick={async () => await api('/api/logistics/ship', 'POST', { orderId: order.id, logisticsCompany: 'SF', trackingNumber: `SF-${order.id}` })}>发货</button>}
    </div>
  )
}

createRoot(document.getElementById('root')).render(<App />)
