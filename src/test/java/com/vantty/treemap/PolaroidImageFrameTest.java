package com.vantty.treemap;

import com.vantty.treemap.util.CamelCaseNameGenerator;
import org.junit.jupiter.api.DisplayNameGeneration;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.TestMethodOrder;

import static org.junit.jupiter.api.Assertions.assertEquals;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@DisplayNameGeneration(CamelCaseNameGenerator.class)
class PolaroidImageFrameTest {

}
