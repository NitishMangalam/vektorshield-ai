import math
from collections import Counter


def calculate_shannon_entropy(text: str) -> float:
    """
    Calculates Shannon Entropy of an incoming prompt payload.
    H(X) = - Σ P(x_i) * log2(P(x_i))
    Higher entropy indicates obfuscated payloads, binary streams, or randomized injection vectors.
    """
    if not text:
        return 0.0

    length = len(text)
    frequency = Counter(text)

    entropy = -sum(
        (count / length) * math.log2(count / length)
        for count in frequency.values()
    )

    return round(entropy, 4)