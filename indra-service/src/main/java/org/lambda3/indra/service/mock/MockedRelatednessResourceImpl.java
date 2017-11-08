package org.lambda3.indra.service.mock;

/*-
 * ==========================License-Start=============================
 * Indra Web Service Module
 * --------------------------------------------------------------------
 * Copyright (C) 2016 - 2017 Lambda^3
 * --------------------------------------------------------------------
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 * ==========================License-End===============================
 */

import org.lambda3.indra.AnalyzedPair;
import org.lambda3.indra.RelatednessResource;
import org.lambda3.indra.ScoredTextPair;
import org.lambda3.indra.TextPair;
import org.lambda3.indra.request.RelatednessOneToManyRequest;
import org.lambda3.indra.request.RelatednessPairRequest;
import org.lambda3.indra.response.RelatednessOneToManyResponse;
import org.lambda3.indra.response.RelatednessPairResponse;

import java.util.*;

/**
 * RelatednessResource implementation that randomly assigns a relatedness value.
 * For testing puporses.
 */
public final class MockedRelatednessResourceImpl implements RelatednessResource {

    private static Random rnd = new Random();

    @Override
    public RelatednessPairResponse getRelatedness(RelatednessPairRequest request) {
        Collection<ScoredTextPair> scored = new ArrayList<>();

        for (TextPair pair : request.getPairs()) {
            AnalyzedPair analyzedPair = new AnalyzedPair(pair);
            ScoredTextPair stp = new ScoredTextPair(analyzedPair, rnd.nextDouble());
            scored.add(stp);
        }

        return new RelatednessPairResponse(request, scored);
    }

    @Override
    public RelatednessOneToManyResponse getRelatedness(RelatednessOneToManyRequest request) {
        Map<String, Double> results = new LinkedHashMap<>();

        for (String m : request.getMany()) {
            results.put(m, rnd.nextDouble());
        }

        return new RelatednessOneToManyResponse(request, results);
    }
}
