<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Is It Phishi? - Assessment Results</title>
    <style>
        body {
            background: linear-gradient(180deg, #0d5c9d 0%, #062b50 100%);
            color: #ffffff;
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 40px 20px;
            min-height: 100vh;
            box-sizing: border-box;
        }

        .container {
            max-width: 650px;
            margin: 0 auto;
        }

        h1.main-title {
            color: #64d2ff;
            font-family: 'Courier New', Courier, monospace;
            font-size: 3rem;
            text-align: center;
            margin-bottom: 40px;
            letter-spacing: 2px;
        }

        .section {
            margin-bottom: 30px;
        }

        .label {
            font-size: 1.1rem;
            margin-bottom: 8px;
        }

        .url-box {
            word-break: break-all;
            font-size: 0.95rem;
            line-height: 1.4;
            margin-bottom: 30px;
        }

        .flex-row {
            display: flex;
            justify-content: space-between;
            margin-bottom: 40px;
        }

        .score-col {
            flex: 1;
        }

        .underline-header {
            font-size: 1.1rem;
            text-decoration: underline;
            margin-bottom: 10px;
        }

        .score-value {
            font-size: 1.8rem;
            font-weight: bold;
            color: #8ce653; /* Light green highlight */
        }

        .breakdown-title {
            text-align: center;
            font-size: 1.6rem;
            text-decoration: underline;
            margin-top: 40px;
            margin-bottom: 30px;
        }

        .breakdown-item {
            margin-bottom: 25px;
            font-size: 1.1rem;
        }

        .breakdown-score {
            color: #dbe653; /* Soft yellow highlight */
            font-weight: bold;
        }
    </style>
</head>
<body>

    <div class="container">
        <!-- Main Title -->
        <h1 class="main-title">Is It Phishi?</h1>

        <!-- Analyzed URL -->
        <div class="section">
            <div class="label">URL:</div>
            <div class="url-box">
                ${not empty analyzedUrl ? analyzedUrl : 'https://www.discord.com/guides/digital-marketing-strategy-small-business/89dcj99:7dbubijbFIJIJa-73bhSkls=?jdoqm=ca41daOe'}
            </div>
        </div>

        <!-- Summary Scores -->
        <div class="flex-row">
            <div class="score-col">
                <div class="underline-header">Risk Assessment Score</div>
                <div class="score-value">
                    ${not empty riskScore ? riskScore : '0'}/100
                </div>
            </div>

            <div class="score-col">
                <div class="underline-header">Threat Level</div>
                <div class="score-value">
                    ${not empty threatLevel ? threatLevel : 'N/A'}
                </div>
            </div>
        </div>

        <!-- Assessment Breakdown -->
        <div class="breakdown-title">Assessment Breakdown</div>

        <div class="breakdown-item">
            URL Structure: 
            <span class="breakdown-score">${not empty urlStructureScore ? urlStructureScore : '0'}/25</span>
        </div>

        <div class="breakdown-item">
            Domain Analysis: 
            <span class="breakdown-score">${not empty domainAnalysisScore ? domainAnalysisScore : '0'}/25</span>
        </div>

        <div class="breakdown-item">
            Web Content: 
            <span class="breakdown-score">${not empty webContentScore ? webContentScore : '0'}/25</span>
        </div>
    </div>

</body>
</html>
