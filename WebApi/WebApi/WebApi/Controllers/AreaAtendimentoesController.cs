using System;
using System.Collections.Generic;
using System.Linq;
using System.Threading.Tasks;
using Microsoft.AspNetCore.Http;
using Microsoft.AspNetCore.Mvc;
using Microsoft.EntityFrameworkCore;
using WebApi.Models;

namespace WebApi.Controllers
{
    [Route("api/[controller]")]
    [ApiController]
    public class AreaAtendimentoesController : ControllerBase
    {
        private readonly ocorrenciasdbContext _context;

        public AreaAtendimentoesController(ocorrenciasdbContext context)
        {
            _context = context;
        }

        // GET: api/AreaAtendimentoes
        [HttpGet]
        public IEnumerable<AreaAtendimento> GetAreaAtendimento()
        {
            return _context.AreaAtendimento;
        }

        // GET: api/AreaAtendimentoes/5
        [HttpGet("{id}")]
        public async Task<IActionResult> GetAreaAtendimento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var areaAtendimento = await _context.AreaAtendimento.FindAsync(id);

            if (areaAtendimento == null)
            {
                return NotFound();
            }

            return Ok(areaAtendimento);
        }

        // PUT: api/AreaAtendimentoes/5
        [HttpPut("{id}")]
        public async Task<IActionResult> PutAreaAtendimento([FromRoute] int id, [FromBody] AreaAtendimento areaAtendimento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            if (id != areaAtendimento.CdAreaatendimento)
            {
                return BadRequest();
            }

            _context.Entry(areaAtendimento).State = EntityState.Modified;

            try
            {
                await _context.SaveChangesAsync();
            }
            catch (DbUpdateConcurrencyException)
            {
                if (!AreaAtendimentoExists(id))
                {
                    return NotFound();
                }
                else
                {
                    throw;
                }
            }

            return NoContent();
        }

        // POST: api/AreaAtendimentoes
        [HttpPost]
        public async Task<IActionResult> PostAreaAtendimento([FromBody] AreaAtendimento areaAtendimento)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            _context.AreaAtendimento.Add(areaAtendimento);
            await _context.SaveChangesAsync();

            return CreatedAtAction("GetAreaAtendimento", new { id = areaAtendimento.CdAreaatendimento }, areaAtendimento);
        }

        // DELETE: api/AreaAtendimentoes/5
        [HttpDelete("{id}")]
        public async Task<IActionResult> DeleteAreaAtendimento([FromRoute] int id)
        {
            if (!ModelState.IsValid)
            {
                return BadRequest(ModelState);
            }

            var areaAtendimento = await _context.AreaAtendimento.FindAsync(id);
            if (areaAtendimento == null)
            {
                return NotFound();
            }

            _context.AreaAtendimento.Remove(areaAtendimento);
            await _context.SaveChangesAsync();

            return Ok(areaAtendimento);
        }

        private bool AreaAtendimentoExists(int id)
        {
            return _context.AreaAtendimento.Any(e => e.CdAreaatendimento == id);
        }
    }
}