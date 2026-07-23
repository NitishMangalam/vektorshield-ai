from fastapi import FastAPI
from pydantic import BaseModel
import time
from core.entropy import calculate_shannon_entropy

app = FastAPI(
    title="VektorShield Intelligence Engine",
    description="Mathematical vector evaluation plane for prompt injection defense"
)


class EvaluationRequest(BaseModel):
    requestId: str
    userId: str
    promptPayload: str
    timestamp: int


class EvaluationResponse(BaseModel):
    requestId: str
    flagged: bool
    riskCategory: str
    riskScore: float
    reasoning: str
    processingMicros: int


@app.post("/api/v1/engine/evaluate", response_model=EvaluationResponse)
async def evaluate_prompt(request: EvaluationRequest):
    start_time = time.perf_counter_ns()

    # Calculate Shannon Entropy
    entropy_score = calculate_shannon_entropy(request.promptPayload)

    # Flag payloads with high randomness/obfuscation
    is_flagged = entropy_score > 4.5
    category = "OBFUSCATED_PAYLOAD" if is_flagged else "NONE"

    elapsed_micros = (time.perf_counter_ns() - start_time) // 1000

    return EvaluationResponse(
        requestId=request.requestId,
        flagged=is_flagged,
        riskCategory=category,
        riskScore=round(min(entropy_score / 8.0, 1.0), 2),
        reasoning=f"Payload entropy evaluated: {entropy_score}. Threat category: {category}",
        processingMicros=elapsed_micros
    )