<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>BC Wellness - Register</title>
        <link rel="stylesheet" href="${pageContext.request.contextPath}/public/styles.css">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/6.0.0/css/all.min.css" rel="stylesheet">
    </head>
    <style>
        * {
            margin: 0;
            padding: 0;
            box-sizing: border-box;
        }

        body {
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
            line-height: 1.6;
            color: #333;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            min-height: 100vh;
        }

        /* Navigation Bar */
        .navbar {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            border-bottom: 1px solid rgba(255, 255, 255, 0.2);
            position: sticky;
            top: 0;
            z-index: 1000;
            box-shadow: 0 2px 20px rgba(0, 0, 0, 0.1);
        }

        .nav-container {
            max-width: 1200px;
            margin: 0 auto;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 20px;
            height: 70px;
        }

        .nav-brand {
            display: flex;
            align-items: center;
            font-size: 1.5rem;
            font-weight: bold;
            color: #4a5568;
        }

        .nav-brand i {
            margin-right: 10px;
            color: #667eea;
            font-size: 1.8rem;
        }

        .nav-center {
            flex: 1;
            display: flex;
            justify-content: center;
        }

        .user-info {
            display: flex;
            align-items: center;
            background: rgba(102, 126, 234, 0.1);
            padding: 8px 16px;
            border-radius: 25px;
            border: 1px solid rgba(102, 126, 234, 0.2);
        }

        .user-avatar {
            width: 40px;
            height: 40px;
            background: linear-gradient(45deg, #667eea, #764ba2);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            margin-right: 10px;
        }

        .user-details {
            display: flex;
            flex-direction: column;
        }

        .user-name {
            font-weight: 600;
            color: #2d3748;
        }

        .user-role {
            font-size: 0.8rem;
            color: #667eea;
        }

        .nav-links {
            display: flex;
            gap: 10px;
        }

        .nav-links a {
            padding: 12px 20px;
            text-decoration: none;
            color: #4a5568;
            border-radius: 8px;
            transition: all 0.3s ease;
            font-weight: 500;
            display: flex;
            align-items: center;
            gap: 8px;
        }

        .nav-links a:hover {
            background: rgba(102, 126, 234, 0.1);
            color: #667eea;
        }

        .logout-btn {
            background: rgba(220, 38, 38, 0.1) !important;
            color: #dc2626 !important;
        }

        .logout-btn:hover {
            background: rgba(220, 38, 38, 0.2) !important;
            color: #dc2626 !important;
        }

        /* Main Container */
        .main-container {
            display: flex;
            justify-content: center;
            align-items: center;
            min-height: calc(100vh - 70px);
            padding: 20px;
        }

        /* Authentication Container */
        .auth-container {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            padding: 40px;
            border-radius: 20px;
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.1);
            width: 100%;
            max-width: 450px;
            border: 1px solid rgba(255, 255, 255, 0.2);
        }

        .register-container {
            max-width: 600px;
        }

        .auth-header {
            text-align: center;
            margin-bottom: 30px;
        }

        .auth-icon {
            width: 80px;
            height: 80px;
            background: linear-gradient(45deg, #667eea, #764ba2);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px;
            color: white;
            font-size: 2rem;
        }

        .auth-header h1 {
            color: #2d3748;
            margin-bottom: 10px;
            font-size: 1.8rem;
        }

        .auth-header p {
            color: #718096;
            font-size: 1rem;
        }

        /* Form Styles */
        .auth-form {
            display: flex;
            flex-direction: column;
            gap: 20px;
        }

        .form-row {
            display: flex;
            gap: 15px;
        }

        .form-group {
            flex: 1;
        }

        .form-group label {
            display: block;
            margin-bottom: 8px;
            color: #4a5568;
            font-weight: 500;
            font-size: 0.95rem;
        }

        .form-group label i {
            margin-right: 8px;
            color: #667eea;
        }

        .form-group input {
            width: 100%;
            padding: 12px 16px;
            border: 2px solid #e2e8f0;
            border-radius: 10px;
            font-size: 1rem;
            transition: all 0.3s ease;
            background: rgba(255, 255, 255, 0.9);
        }

        .form-group input:focus {
            outline: none;
            border-color: #667eea;
            box-shadow: 0 0 0 3px rgba(102, 126, 234, 0.1);
        }

        .form-group input::placeholder {
            color: #a0aec0;
        }

        /* Password Input */
        .password-input {
            position: relative;
        }

        .password-toggle {
            position: absolute;
            right: 12px;
            top: 50%;
            transform: translateY(-50%);
            background: none;
            border: none;
            color: #718096;
            cursor: pointer;
            padding: 4px;
            border-radius: 4px;
            transition: color 0.3s ease;
        }

        .password-toggle:hover {
            color: #667eea;
        }

        /* Password Strength */
        .password-strength {
            margin-top: 8px;
        }

        .strength-bar {
            height: 4px;
            background: #e2e8f0;
            border-radius: 2px;
            overflow: hidden;
            margin-bottom: 4px;
        }

        .strength-fill {
            height: 100%;
            width: 0%;
            border-radius: 2px;
            transition: all 0.3s ease;
        }

        .strength-text {
            font-size: 0.8rem;
            color: #666;
        }

        /* Buttons */
        .btn {
            padding: 14px 24px;
            border: none;
            border-radius: 10px;
            font-size: 1rem;
            font-weight: 600;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
            gap: 8px;
            text-decoration: none;
        }

        .btn-primary {
            background: linear-gradient(45deg, #667eea, #764ba2);
            color: white;
            box-shadow: 0 4px 15px rgba(102, 126, 234, 0.4);
        }

        .btn-primary:hover {
            transform: translateY(-2px);
            box-shadow: 0 6px 20px rgba(102, 126, 234, 0.6);
        }

        .btn-secondary {
            background: rgba(102, 126, 234, 0.1);
            color: #667eea;
            border: 1px solid rgba(102, 126, 234, 0.2);
        }

        .btn-secondary:hover {
            background: rgba(102, 126, 234, 0.2);
        }

        /* Alerts */
        .alert {
            padding: 12px 16px;
            border-radius: 10px;
            margin-bottom: 20px;
            display: flex;
            align-items: center;
            gap: 10px;
            font-size: 0.9rem;
        }

        .alert-error {
            background: rgba(220, 38, 38, 0.1);
            color: #dc2626;
            border: 1px solid rgba(220, 38, 38, 0.2);
        }

        .alert-success {
            background: rgba(34, 197, 94, 0.1);
            color: #22c55e;
            border: 1px solid rgba(34, 197, 94, 0.2);
        }

        /* Auth Footer */
        .auth-footer {
            text-align: center;
            margin-top: 30px;
            padding-top: 20px;
            border-top: 1px solid #e2e8f0;
        }

        .auth-footer p {
            color: #718096;
            font-size: 0.9rem;
        }

        .auth-footer a {
            color: #667eea;
            text-decoration: none;
            font-weight: 500;
        }

        .auth-footer a:hover {
            text-decoration: underline;
        }

        /* Dashboard Styles */
        .dashboard-container {
            max-width: 1200px;
            margin: 0 auto;
            padding: 20px;
        }

        .dashboard-header {
            text-align: center;
            margin-bottom: 40px;
            color: white;
        }

        .dashboard-header h1 {
            font-size: 2.5rem;
            margin-bottom: 10px;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
        }

        .dashboard-header p {
            font-size: 1.1rem;
            opacity: 0.9;
        }

        /* Stats Cards */
        .dashboard-stats {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 20px;
            margin-bottom: 40px;
        }

        .stat-card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            padding: 30px;
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.2);
            display: flex;
            align-items: center;
            gap: 20px;
            transition: transform 0.3s ease;
        }

        .stat-card:hover {
            transform: translateY(-5px);
        }

        .stat-icon {
            width: 60px;
            height: 60px;
            background: linear-gradient(45deg, #667eea, #764ba2);
            border-radius: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 1.5rem;
        }

        .stat-content h3 {
            font-size: 2rem;
            color: #2d3748;
            margin-bottom: 5px;
        }

        .stat-content p {
            color: #718096;
            font-size: 1rem;
        }

        /* Table Container */
        .table-container {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            border-radius: 15px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.2);
            overflow: hidden;
        }

        .table-header {
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 20px 30px;
            border-bottom: 1px solid #e2e8f0;
            background: rgba(102, 126, 234, 0.05);
        }

        .table-header h2 {
            color: #2d3748;
            font-size: 1.5rem;
        }

        .table-actions {
            display: flex;
            gap: 15px;
            align-items: center;
        }

        .search-box {
            position: relative;
            display: flex;
            align-items: center;
        }

        .search-box i {
            position: absolute;
            left: 12px;
            color: #718096;
        }

        .search-box input {
            padding: 10px 12px 10px 35px;
            border: 2px solid #e2e8f0;
            border-radius: 8px;
            font-size: 0.9rem;
            width: 250px;
            transition: border-color 0.3s ease;
        }

        .search-box input:focus {
            outline: none;
            border-color: #667eea;
        }

        /* Modern Table */
        .table-wrapper {
            overflow-x: auto;
        }

        .modern-table {
            width: 100%;
            border-collapse: collapse;
            font-size: 0.9rem;
        }

        .modern-table th {
            background: rgba(102, 126, 234, 0.1);
            color: #4a5568;
            padding: 15px 20px;
            text-align: left;
            font-weight: 600;
            border-bottom: 2px solid #e2e8f0;
            cursor: pointer;
            transition: background-color 0.3s ease;
        }

        .modern-table th:hover {
            background: rgba(102, 126, 234, 0.2);
        }

        .modern-table th i.fa-sort {
            margin-left: 8px;
            opacity: 0.6;
            font-size: 0.8rem;
        }

        .modern-table td {
            padding: 15px 20px;
            border-bottom: 1px solid #f7fafc;
            color: #4a5568;
            vertical-align: middle;
        }

        .modern-table tbody tr {
            transition: background-color 0.3s ease;
        }

        .modern-table tbody tr:hover {
            background: rgba(102, 126, 234, 0.05);
        }

        .modern-table tbody tr:nth-child(even) {
            background: rgba(247, 250, 252, 0.5);
        }

        .modern-table tbody tr:nth-child(even):hover {
            background: rgba(102, 126, 234, 0.05);
        }

        /* Table Cell Styles */
        .student-number {
            font-weight: 600;
            color: #667eea;
            background: rgba(102, 126, 234, 0.1);
            padding: 4px 8px;
            border-radius: 6px;
            font-size: 0.85rem;
        }

        .user-cell {
            display: flex;
            align-items: center;
            gap: 10px;
        }

        .user-avatar-small {
            width: 35px;
            height: 35px;
            background: linear-gradient(45deg, #667eea, #764ba2);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            color: white;
            font-size: 0.9rem;
        }

        .email-link, .phone-link {
            color: #667eea;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .email-link:hover, .phone-link:hover {
            color: #764ba2;
            text-decoration: underline;
        }

        /* Action Buttons */
        .action-buttons {
            display: flex;
            gap: 5px;
        }

        .btn-icon {
            width: 35px;
            height: 35px;
            border: none;
            border-radius: 8px;
            cursor: pointer;
            transition: all 0.3s ease;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 0.9rem;
        }

        .btn-view {
            background: rgba(59, 130, 246, 0.1);
            color: #3b82f6;
        }

        .btn-view:hover {
            background: rgba(59, 130, 246, 0.2);
        }

        .btn-edit {
            background: rgba(245, 158, 11, 0.1);
            color: #f59e0b;
        }

        .btn-edit:hover {
            background: rgba(245, 158, 11, 0.2);
        }

        .btn-delete {
            background: rgba(239, 68, 68, 0.1);
            color: #ef4444;
        }

        .btn-delete:hover {
            background: rgba(239, 68, 68, 0.2);
        }

        /* Empty State */
        .no-data {
            text-align: center;
            padding: 60px 20px;
        }

        .empty-state {
            display: flex;
            flex-direction: column;
            align-items: center;
            gap: 15px;
        }

        .empty-state i {
            font-size: 4rem;
            color: #cbd5e0;
        }

        .empty-state h3 {
            color: #4a5568;
            font-size: 1.5rem;
        }

        .empty-state p {
            color: #718096;
            font-size: 1rem;
        }

        /* Responsive Design */
        @media (max-width: 768px) {
            .nav-container {
                flex-direction: column;
                height: auto;
                padding: 15px;
            }

            .nav-center {
                margin: 15px 0;
            }

            .nav-links {
                flex-direction: column;
                width: 100%;
                gap: 5px;
            }

            .nav-links a {
                justify-content: center;
            }

            .auth-container {
                margin: 20px;
                padding: 30px 20px;
            }

            .form-row {
                flex-direction: column;
            }

            .dashboard-stats {
                grid-template-columns: 1fr;
            }

            .table-header {
                flex-direction: column;
                gap: 15px;
                align-items: stretch;
            }

            .table-actions {
                flex-direction: column;
                gap: 10px;
            }

            .search-box input {
                width: 100%;
            }

            .modern-table th,
            .modern-table td {
                padding: 10px;
                font-size: 0.8rem;
            }

            .action-buttons {
                flex-direction: column;
                gap: 3px;
            }

            .btn-icon {
                width: 30px;
                height: 30px;
                font-size: 0.8rem;
            }
        }

        @media (max-width: 480px) {
            .dashboard-header h1 {
                font-size: 2rem;
            }

            .stat-card {
                padding: 20px;
            }

            .stat-content h3 {
                font-size: 1.5rem;
            }

            .modern-table {
                font-size: 0.7rem;
            }

            .modern-table th,
            .modern-table td {
                padding: 8px;
            }
        }

        /* HOME PAGE STYLES */

        /* Home Container */
        .home-container {
            min-height: calc(100vh - 70px);
        }

        /* Hero Section */
        .hero-section {
            min-height: 80vh;
            display: flex;
            align-items: center;
            padding: 60px 20px;
            background: linear-gradient(135deg, #667eea 0%, #764ba2 100%);
            position: relative;
            overflow: hidden;
        }

        .hero-section::before {
            content: '';
            position: absolute;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            background: url('data:image/svg+xml,<svg xmlns="http://www.w3.org/2000/svg" viewBox="0 0 1000 100" fill="white" opacity="0.1"><polygon points="0,100 1000,0 1000,100"/></svg>');
            background-size: cover;
            background-position: bottom;
        }

        .hero-content {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 60px;
            align-items: center;
            position: relative;
            z-index: 1;
        }

        .hero-text {
            color: white;
        }

        .hero-text h1 {
            font-size: 3.5rem;
            font-weight: 700;
            margin-bottom: 20px;
            line-height: 1.1;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
        }

        .highlight {
            background: linear-gradient(45deg, #ffd700, #ffeb3b);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }

        .hero-subtitle {
            font-size: 1.5rem;
            margin-bottom: 15px;
            opacity: 0.9;
            font-weight: 300;
        }

        .hero-description {
            font-size: 1.1rem;
            margin-bottom: 30px;
            opacity: 0.8;
            line-height: 1.6;
        }

        .hero-buttons {
            display: flex;
            gap: 15px;
            flex-wrap: wrap;
        }

        .btn-large {
            padding: 16px 32px;
            font-size: 1.1rem;
            border-radius: 12px;
        }

        .btn-outline {
            background: transparent;
            color: white;
            border: 2px solid white;
        }

        .btn-outline:hover {
            background: white;
            color: #667eea;
        }

        /* Hero Graphics */
        .hero-graphic {
            position: relative;
            display: flex;
            align-items: center;
            justify-content: center;
            height: 400px;
        }

        .wellness-icon {
            width: 150px;
            height: 150px;
            background: rgba(255, 255, 255, 0.2);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 4rem;
            color: white;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.3);
            animation: pulse 2s infinite;
        }

        .floating-elements {
            position: absolute;
            width: 100%;
            height: 100%;
        }

        .floating-element {
            position: absolute;
            width: 60px;
            height: 60px;
            background: rgba(255, 255, 255, 0.15);
            border-radius: 50%;
            display: flex;
            align-items: center;
            justify-content: center;
            font-size: 1.5rem;
            color: white;
            backdrop-filter: blur(10px);
            border: 1px solid rgba(255, 255, 255, 0.2);
            animation: float 3s ease-in-out infinite;
        }

        .element-1 {
            top: 10%;
            left: 20%;
            animation-delay: 0s;
        }

        .element-2 {
            top: 20%;
            right: 15%;
            animation-delay: 0.5s;
        }

        .element-3 {
            bottom: 20%;
            left: 15%;
            animation-delay: 1s;
        }

        .element-4 {
            bottom: 10%;
            right: 20%;
            animation-delay: 1.5s;
        }

        @keyframes pulse {
            0%, 100% { transform: scale(1); }
            50% { transform: scale(1.05); }
        }

        @keyframes float {
            0%, 100% { transform: translateY(0px); }
            50% { transform: translateY(-10px); }
        }

        /* Section Styles */
        .section-header {
            text-align: center;
            margin-bottom: 50px;
            color: white;
        }

        .section-header h2 {
            font-size: 2.5rem;
            margin-bottom: 15px;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
        }

        .section-header p {
            font-size: 1.2rem;
            opacity: 0.9;
        }

        /* Features Section */
        .features-section {
            padding: 80px 20px;
            background: rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(10px);
        }

        .features-grid {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
            gap: 30px;
        }

        .feature-card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            padding: 40px 30px;
            border-radius: 20px;
            text-align: center;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.2);
            transition: all 0.3s ease;
        }

        .feature-card:hover {
            transform: translateY(-10px);
            box-shadow: 0 20px 40px rgba(0, 0, 0, 0.15);
        }

        .feature-icon {
            width: 80px;
            height: 80px;
            background: linear-gradient(45deg, #667eea, #764ba2);
            border-radius: 20px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin: 0 auto 20px;
            font-size: 2rem;
            color: white;
        }

        .feature-card h3 {
            font-size: 1.3rem;
            margin-bottom: 15px;
            color: #2d3748;
        }

        .feature-card p {
            color: #718096;
            line-height: 1.6;
        }

        /* Services Section */
        .services-section {
            padding: 80px 20px;
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
        }

        .services-grid {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(350px, 1fr));
            gap: 30px;
        }

        .service-card {
            background: rgba(255, 255, 255, 0.95);
            backdrop-filter: blur(10px);
            padding: 40px 30px;
            border-radius: 20px;
            box-shadow: 0 10px 30px rgba(0, 0, 0, 0.1);
            border: 1px solid rgba(255, 255, 255, 0.2);
            transition: all 0.3s ease;
        }

        .service-card:hover {
            transform: translateY(-5px);
            box-shadow: 0 15px 35px rgba(0, 0, 0, 0.15);
        }

        .service-icon {
            width: 70px;
            height: 70px;
            background: linear-gradient(45deg, #667eea, #764ba2);
            border-radius: 15px;
            display: flex;
            align-items: center;
            justify-content: center;
            margin-bottom: 20px;
            font-size: 1.8rem;
            color: white;
        }

        .service-card h3 {
            font-size: 1.4rem;
            margin-bottom: 15px;
            color: #2d3748;
        }

        .service-card p {
            color: #718096;
            margin-bottom: 20px;
            line-height: 1.6;
        }

        .service-card ul {
            list-style: none;
            padding: 0;
        }

        .service-card li {
            color: #4a5568;
            margin-bottom: 8px;
            padding-left: 20px;
            position: relative;
        }

        .service-card li:before {
            content: '✓';
            position: absolute;
            left: 0;
            color: #667eea;
            font-weight: bold;
        }

        /* Stats Section */
        .stats-section {
            padding: 60px 20px;
            background: rgba(255, 255, 255, 0.1);
            backdrop-filter: blur(10px);
        }

        .stats-container {
            max-width: 1000px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(200px, 1fr));
            gap: 30px;
        }

        .stat-item {
            text-align: center;
            color: white;
            padding: 20px;
            opacity: 0;
            transform: translateY(20px);
            transition: all 0.6s ease;
        }

        .stat-item.animate {
            opacity: 1;
            transform: translateY(0);
        }

        .stat-number {
            font-size: 3rem;
            font-weight: 700;
            margin-bottom: 10px;
            background: linear-gradient(45deg, #ffd700, #ffeb3b);
            -webkit-background-clip: text;
            -webkit-text-fill-color: transparent;
            background-clip: text;
        }

        .stat-label {
            font-size: 1.1rem;
            opacity: 0.9;
        }

        /* CTA Section */
        .cta-section {
            padding: 80px 20px;
            background: rgba(255, 255, 255, 0.05);
            backdrop-filter: blur(10px);
            text-align: center;
        }

        .cta-content {
            max-width: 800px;
            margin: 0 auto;
        }

        .cta-content h2 {
            font-size: 2.5rem;
            margin-bottom: 20px;
            color: white;
            text-shadow: 0 2px 4px rgba(0, 0, 0, 0.3);
        }

        .cta-content p {
            font-size: 1.2rem;
            margin-bottom: 30px;
            color: white;
            opacity: 0.9;
        }

        .cta-buttons {
            display: flex;
            gap: 20px;
            justify-content: center;
            flex-wrap: wrap;
        }

        /* Footer */
        .footer {
            background: rgba(0, 0, 0, 0.9);
            color: white;
            padding: 50px 20px 20px;
        }

        .footer-content {
            max-width: 1200px;
            margin: 0 auto;
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(250px, 1fr));
            gap: 40px;
        }

        .footer-brand {
            display: flex;
            align-items: center;
            font-size: 1.5rem;
            font-weight: bold;
            margin-bottom: 15px;
        }

        .footer-brand i {
            margin-right: 10px;
            color: #667eea;
        }

        .footer-section h4 {
            margin-bottom: 15px;
            font-size: 1.2rem;
            color: #667eea;
        }

        .footer-section ul {
            list-style: none;
            padding: 0;
        }

        .footer-section li {
            margin-bottom: 8px;
        }

        .footer-section a {
            color: #cbd5e0;
            text-decoration: none;
            transition: color 0.3s ease;
        }

        .footer-section a:hover {
            color: #667eea;
        }

        .footer-section p {
            color: #cbd5e0;
            line-height: 1.6;
        }

        .footer-bottom {
            max-width: 1200px;
            margin: 0 auto;
            text-align: center;
            padding-top: 30px;
            border-top: 1px solid rgba(255, 255, 255, 0.1);
            color: #a0aec0;
        }

        /* Responsive Design for Home Page */
        @media (max-width: 768px) {
            .hero-content {
                grid-template-columns: 1fr;
                text-align: center;
                gap: 40px;
            }

            .hero-text h1 {
                font-size: 2.5rem;
            }

            .hero-buttons {
                justify-content: center;
            }

            .hero-graphic {
                height: 300px;
            }

            .wellness-icon {
                width: 120px;
                height: 120px;
                font-size: 3rem;
            }

            .floating-element {
                width: 50px;
                height: 50px;
                font-size: 1.2rem;
            }

            .features-grid,
            .services-grid {
                grid-template-columns: 1fr;
            }

            .stats-container {
                grid-template-columns: repeat(2, 1fr);
            }

            .cta-buttons {
                flex-direction: column;
                align-items: center;
            }

            .footer-content {
                grid-template-columns: 1fr;
                text-align: center;
            }
        }

        @media (max-width: 480px) {
            .hero-text h1 {
                font-size: 2rem;
            }

            .section-header h2 {
                font-size: 2rem;
            }

            .stat-number {
                font-size: 2.5rem;
            }

            .cta-content h2 {
                font-size: 2rem;
            }

            .stats-container {
                grid-template-columns: 1fr;
            }
        }

    </style>
    <body>
        <div class="navbar">
            <div class="nav-container">
                <div class="nav-brand">
                    <i class="fas fa-heartbeat"></i>
                    <span>BC Wellness</span>
                </div>
                <div class="nav-links">
                    <a href="http://localhost:8080/PRG381_Group_V2_WebApp/index">
                        <i class="fas fa-home"></i> Home
                    </a>
                    <a href="http://localhost:8080/PRG381_Group_V2_WebApp/login">
                        <i class="fas fa-sign-in-alt"></i> Login
                    </a>
                    <a href="http://localhost:8080/PRG381_Group_V2_WebApp/dashboard">
                        <i class="fas fa-tachometer-alt"></i> Dashboard
                    </a>
                </div>
            </div>
        </div>

        <div class="main-container">
            <div class="auth-container register-container">
                <div class="auth-header">
                    <div class="auth-icon">
                        <i class="fas fa-user-plus"></i>
                    </div>
                    <h1>Join BC Wellness</h1>
                    <p>Create your account to access wellness services</p>
                </div>

                <c:if test="${not empty errorMessage}">
                    <div class="alert alert-error">
                        <i class="fas fa-exclamation-triangle"></i>
                        <span>${errorMessage}</span>
                    </div>
                </c:if>

                <form action="register" method="post" class="auth-form">
                    <div class="form-row">
                        <div class="form-group">
                            <label for="studentNumber">
                                <i class="fas fa-id-card"></i> Student Number
                            </label>
                            <input type="text" 
                                   id="studentNumber" 
                                   name="studentNumber" 
                                   placeholder="e.g., 12345678"
                                   required
                                   pattern="[0-9]{8}"
                                   title="Student number must be 8 digits">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="name">
                                <i class="fas fa-user"></i> First Name
                            </label>
                            <input type="text" 
                                   id="name" 
                                   name="name" 
                                   placeholder="Enter your first name"
                                   required
                                   autocomplete="given-name">
                        </div>

                        <div class="form-group">
                            <label for="surname">
                                <i class="fas fa-user"></i> Last Name
                            </label>
                            <input type="text" 
                                   id="surname" 
                                   name="surname" 
                                   placeholder="Enter your last name"
                                   required
                                   autocomplete="family-name">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="email">
                                <i class="fas fa-envelope"></i> Email Address
                            </label>
                            <input type="email" 
                                   id="email" 
                                   name="email" 
                                   placeholder="your.email@example.com"
                                   required
                                   autocomplete="email">
                        </div>

                        <div class="form-group">
                            <label for="phone">
                                <i class="fas fa-phone"></i> Phone Number
                            </label>
                            <input type="tel" 
                                   id="phone" 
                                   name="phone" 
                                   placeholder="e.g., +27 12 345 6789"
                                   required
                                   autocomplete="tel">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label for="password">
                                <i class="fas fa-lock"></i> Password
                            </label>
                            <div class="password-input">
                                <input type="password" 
                                       id="password" 
                                       name="password" 
                                       placeholder="Create a strong password"
                                       required
                                       autocomplete="new-password"
                                       minlength="8">
                                <button type="button" class="password-toggle" onclick="togglePassword('password', 'toggleIcon1')">
                                    <i class="fas fa-eye" id="toggleIcon1"></i>
                                </button>
                            </div>
                            <div class="password-strength">
                                <div class="strength-bar">
                                    <div class="strength-fill" id="strengthBar"></div>
                                </div>
                                <span class="strength-text" id="strengthText">Password strength</span>
                            </div>
                        </div>
                    </div>

                    <button type="submit" class="btn btn-primary">
                        <i class="fas fa-user-plus"></i> Create Account
                    </button>
                </form>

                <div class="auth-footer">
                    <p>Already have an account? 
                        <a href="http://localhost:8080/PRG381_Group_V2_WebApp/login">Sign in here</a>
                    </p>
                </div>
            </div>
        </div>

        <script>
            function togglePassword(inputId, iconId) {
                const passwordInput = document.getElementById(inputId);
                const toggleIcon = document.getElementById(iconId);
                
                if (passwordInput.type === 'password') {
                    passwordInput.type = 'text';
                    toggleIcon.classList.remove('fa-eye');
                    toggleIcon.classList.add('fa-eye-slash');
                } else {
                    passwordInput.type = 'password';
                    toggleIcon.classList.remove('fa-eye-slash');
                    toggleIcon.classList.add('fa-eye');
                }
            }

            // Password strength checker
            document.getElementById('password').addEventListener('input', function() {
                const password = this.value;
                const strengthBar = document.getElementById('strengthBar');
                const strengthText = document.getElementById('strengthText');
                
                let strength = 0;
                let feedback = '';
                
                if (password.length >= 8) strength++;
                if (password.match(/[a-z]/)) strength++;
                if (password.match(/[A-Z]/)) strength++;
                if (password.match(/[0-9]/)) strength++;
                if (password.match(/[^a-zA-Z0-9]/)) strength++;
                
                const strengthLevels = ['Very Weak', 'Weak', 'Fair', 'Good', 'Strong'];
                const strengthColors = ['#ff4444', '#ff8800', '#ffcc00', '#88cc00', '#44aa44'];
                
                if (password.length === 0) {
                    strengthBar.style.width = '0%';
                    strengthText.textContent = 'Password strength';
                    strengthText.style.color = '#666';
                } else {
                    const percentage = (strength / 5) * 100;
                    strengthBar.style.width = percentage + '%';
                    strengthBar.style.backgroundColor = strengthColors[strength - 1];
                    strengthText.textContent = strengthLevels[strength - 1];
                    strengthText.style.color = strengthColors[strength - 1];
                }
            });
        </script>
    </body>
</html>
